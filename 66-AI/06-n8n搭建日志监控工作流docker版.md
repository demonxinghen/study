```text
docker版本26.1.3
docker compose版本2.27.0
```

1.安装docker
```shell
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum install -y docker-ce docker-ce-cli containerd.io
sudo systemctl start docker
sudo systemctl enable docker
```
2.安装docker-compose
```shell
# 新版本docker已内置docker compose,以下命令查看
docker compose version
```
3.新建目录
```shell
mkdir /usr/local/n8n
cd /usr/local/n8n
```
4.编写docker-compose.yml
```yaml
services:
  postgres:
    image: postgres:17.5
    restart: always
    container_name: postgres
    environment:
      POSTGRES_USER: n8nuser
      POSTGRES_PASSWORD: secure_password_123
      POSTGRES_DB: n8n
      PGDATA: /var/lib/postgresql/data/pgdata
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U n8nuser -d n8n"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 30s

  n8n:
    image: docker.n8n.io/n8nio/n8n
    restart: always
    container_name: n8n
    ports:
      - "5678:5678"
    volumes:
      - n8n_data:/home/node/.n8n
      - /home/logs:/home/logs # 挂载日志目录
    environment:
      N8N_BASIC_AUTH_ACTIVE: "true"
      N8N_BASIC_AUTH_USER: "admin"
      N8N_BASIC_AUTH_PASSWORD: "secure_password_123"
      N8N_PUSH_BACKEND: "websocket"
      DB_TYPE: "postgresdb"
      DB_POSTGRESDB_HOST: "postgres"
      DB_POSTGRESDB_PORT: "5432"
      DB_POSTGRESDB_DATABASE: "n8n"
      DB_POSTGRESDB_USER: "n8nuser"
      DB_POSTGRESDB_PASSWORD: "secure_password_123"
      N8N_RUNNERS_ENABLED: "true"
    depends_on:
      postgres:
        condition: service_healthy

volumes:
  pgdata:
  n8n_data:
```
5.启动postgresql
```shell
docker compose up -d
```
6.下载镜像不方便的话，可以直接使用目录下的postgresql.tar和n8n.tar
```shell
docker load -i postgresql.tar
docker load -i n8n.tar
```
7.删除
```shell
docker compose down
docker volume rm $(docker volume ls -q) 2>/dev/null || true
docker system prune -f
```
8.command节点修改

由于05文档中是远程ssh读取日志，现在是在同一台服务器，直接读取，也就不再使用ssh节点，直接使用command节点，修改如下：
```shell
LATEST_LOG=$(ls -t /home/logs/*.log 2>/dev/null | head -1); if [ -f "$LATEST_LOG" ]; then LAST_POS_FILE="/tmp/n8n_log_position"; if [ -f "$LAST_POS_FILE" ]; then LAST_POS=$(cat "$LAST_POS_FILE"); else LAST_POS=0; fi; TMP_BODY="/tmp/n8n_log_body.log"; TMP_OUT="/tmp/n8n_log_output.log"; BYTES=$(tail -c +$((LAST_POS + 1)) "$LATEST_LOG" | tee >(awk '/^[0-9]{2}:[0-9]{2}:[0-9]{2}\.[0-9]{3}/ { if (buf) print buf; buf = $0; next } { buf = buf " " $0 } END { if (buf) print buf }' | head -1000 > "$TMP_BODY") | wc -c); NEW_POS=$((LAST_POS + BYTES)); echo "$NEW_POS" > "$LAST_POS_FILE"; echo -e "filename:$LATEST_LOG\n$(cat "$TMP_BODY")" > "$TMP_OUT"; echo "Log written to $TMP_OUT"; else echo "No log files found"; fi
```
此处是将日志输出到/tmp/n8n_log_output.log文件中，因为在直接echo的情况下，出现了一个错误
```text
Problem in node ‘Execute Command‘
stdout maxBuffer length exceeded
```
官网解释如下
```text
Error: stdout maxBuffer length exceeded
This error happens when your command returns more output than the Execute Command node is able to process at one time.

To avoid this error, reduce output your command produces. Check your command's manual page or documentation to see if there are flags to limit or filter output. If not, you may need to pipe the output to another command to remove unneeded info.
```
所以只能选择输出到指定文件
9.新增Read/Write Files from Disk节点
```text
Operation: Read File(s) From Disk
File(s) Selector: /tmp/n8n_log_output.log
```
10.code节点修改
```javascript
// 由于输出到了文件，所以获取节点也需要修改，这是原来的代码
// const logOutput = $input.first().json.stdout;
// 改为
const buffer = $input.first().binary.data.data;
const logOutput = Buffer.from(buffer, 'base64').toString('utf-8');
// 会提示Property 'data' does not exist on type 'N8nBinary'，但是不影响使用，所以忽略
// 会提示Cannot find name 'Buffer'. Did you mean 'buffer'?，但是不影响使用，所以忽略
```

问题解决：
```text
报错 connection lost
You have a connection issue or the server is down.
n8n should reconnect automatically once the issue is resolved.

解决方法：增加配置N8N_PUSH_BACKEND: "websocket"
nginx增加配置
server {
  listen 443 ssl;
  server_name server.name;

  ssl_certificate     /usr/local/nginx/cert/ddd.pem;
  ssl_certificate_key /usr/local/nginx/cert/ddd.key;

  location / {
    proxy_pass http://localhost:5678;
    proxy_set_header Host $host;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_http_version 1.1; # Required for WebSocket
    proxy_set_header Upgrade $http_upgrade; # Required for WebSocket
    proxy_set_header Connection "upgrade"; # Required for WebSocket
  }
}
```
```shell
# 定时任务读取命令
LATEST_LOG=$(ls -t /home/logs/*.log 2>/dev/null | head -1); if [ -f "$LATEST_LOG" ]; then LAST_POS_FILE="/tmp/n8n_log_position"; [ -f "$LAST_POS_FILE" ] && LAST_FILE_LINE=$(cat "$LAST_POS_FILE") || LAST_FILE_LINE=""; LAST_FILE=$(echo "$LAST_FILE_LINE" | cut -d':' -f1); LAST_POS=$(echo "$LAST_FILE_LINE" | cut -d':' -f2); [ "$LATEST_LOG" != "$LAST_FILE" ] && LAST_POS=0; TMP_BODY="/tmp/n8n_log_body.log"; TMP_OUT="/tmp/n8n_log_output.log"; BYTES=$(tail -c +$((LAST_POS + 1)) "$LATEST_LOG" | tee >(awk '/^[0-9]{2}:[0-9]{2}:[0-9]{2}\.[0-9]{3}/ {if (buf) print buf; buf = $0; next} {buf = buf " " $0} END {if (buf) print buf}' | head -1000 > "$TMP_BODY") | wc -c); NEW_POS=$((LAST_POS + BYTES)); echo "$LATEST_LOG:$NEW_POS" > "$LAST_POS_FILE"; echo -e "filename:$LATEST_LOG\n$(cat "$TMP_BODY")" > "$TMP_OUT"; echo "Log written to $TMP_OUT"; else echo "No log files found"; fi
# 手动触发指定读取某个日志文件，每次读取一万行
LOG_FILE="/home/logs/major.2025-07-15.0.log"; LOG_NAME=$(basename $LOG_FILE); LAST_POS_FILE="/tmp/n8n_manual_log_position_${USER}_${LOG_NAME}"; if [ -f "$LOG_FILE" ]; then echo "filename:$LOG_FILE"; if [ -f "$LAST_POS_FILE" ]; then LAST_POS=$(cat "$LAST_POS_FILE"); else LAST_POS=0; fi; CHUNK=$(tail -c +$((LAST_POS + 1)) "$LOG_FILE" | head -n 10000); NEW_LINES=$(echo "$CHUNK" | awk '/^[0-9]{2}:[0-9]{2}:[0-9]{2}\.[0-9]{3}/ { if (buf) print buf; buf = $0; next } { buf = buf " " $0 } END { if (buf) print buf }'); if [ ! -z "$NEW_LINES" ]; then echo "$NEW_LINES"; fi; echo $((LAST_POS + $(echo "$CHUNK" | wc -c))) > "$LAST_POS_FILE"; else echo "Log file not found: $LOG_FILE"; fi
```