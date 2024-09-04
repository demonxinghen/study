### 安装anaconda
镜像地址：https://mirrors.tuna.tsinghua.edu.cn/anaconda/archive/

安装时不要勾选添加环境变量,后续会手动添加,对应的英文提示是Add Anaconda3 to my PATH environment variable

安装好后配置环境变量,假设安装在D:\Anaconda3

在path里添加

D:\Anaconda3

D:\Anaconda3\Scripts

### 安装Visual Studio 2022
为了其中的C++编译器

下载地址：https://visualstudio.microsoft.com/zh-hans/downloads/

需要勾选的有：

工作负荷-通用Windows平台开发，以及右侧的C++(v142)通用Windows平台工具，桌面版C++

单个组件-用于Windows的C++ CMake工具

### 安装make
administrator权限打开PowerShell,执行命令
```shell
Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

choco install make
```

[//]: # (安装后打开MinGW Installation Manager)

[//]: # ()
[//]: # (勾选mingw32-gcc-g++，点击Installation->Apply Changes)

### 安装git
下载地址：https://git-scm.com/download/win

### 配置Python环境

在开始菜单搜索Anaconda Prompt,右键以管理员身份运行

测试一下环境是不是配好了

```shell
conda --version

python --version
# 需要python是3.11版本
git --version
```

### 获取源码
```shell
git clone git@github.com:zylon-ai/private-gpt.git
cd privateGPT
```

### 配置依赖并安装模型
```shell
pip install poetry
# 旧版本使用poetry install --with ui, local
poetry install --extras ui
poetry run python scripts/setup
poetry install --extras "ui llms-llama-cpp vector-stores-qdrant embeddings-huggingface"
```

### 启动本地服务
```shell
set PGPT_PROFILES=local
make run
```
### 如果要使用ollama服务
```shell
poetry install --extras "ui llms-ollama embeddings-ollama vector-stores-qdrant"
set PGPT_PROFILES=ollama
make run
```

### 修改settings-ollama.yaml配置
```yaml
server:
  env_name: ${APP_ENV:ollama}

llm:
  mode: ollama
  max_new_tokens: 1024 # default is 512
  context_window: 8000 # default is 3900
  temperature: 0.1     #The temperature of the model. Increasing the temperature will make the model answer more creatively. A value of 0.1 would be more factual. (Default: 0.1)

embedding:
  mode: ollama

ollama:
  llm_model: llama2:13b
  embedding_model: llama2:13b # default is nomic-embed-text
  api_base: http://localhost:11434
  tfs_z: 1.0              # Tail free sampling is used to reduce the impact of less probable tokens from the output. A higher value (e.g., 2.0) will reduce the impact more, while a value of 1.0 disables this setting.
  top_k: 40               # Reduces the probability of generating nonsense. A higher value (e.g. 100) will give more diverse answers, while a lower value (e.g. 10) will be more conservative. (Default: 40)
  top_p: 0.9              # Works together with top-k. A higher value (e.g., 0.95) will lead to more diverse text, while a lower value (e.g., 0.5) will generate more focused and conservative text. (Default: 0.9)
  repeat_last_n: 64       # Sets how far back for the model to look back to prevent repetition. (Default: 64, 0 = disabled, -1 = num_ctx)
  repeat_penalty: 1.2     # Sets how strongly to penalize repetitions. A higher value (e.g., 1.5) will penalize repetitions more strongly, while a lower value (e.g., 0.9) will be more lenient. (Default: 1.1)
  request_timeout: 120.0  # Time elapsed until ollama times out the request. Default is 120s. Format is float.

vectorstore:
  database: qdrant

qdrant:
  path: ollama/private_gpt/qdrant
```


### 访问本地服务
在浏览器输入http://localhost:8001

### 遇到的问题
问题1：No module named 'gradio'
```text
解决方法：pip install gradio
```
问题2：ModuleNotFoundError: No module named 'llama_index.llms.llama_cpp'
```text
遇到这种module中带.的,无法直接通过pip install安装,需要手动安装
使用pycharm打开项目privateGPT,load结束后,将llama_index整个拷贝到D:\Anaconda3\Lib\site-packages里
拷贝后应该是D:\Anaconda3\Lib\site-packages\llama_index\llms\llama_cpp这样的目录结构
需要这么解决的有llama_cpp, llama_index.llms.llama_cpp, pydantic.v1
```
问题3：Provided model path does not exist. Please check the path or provide a model_url to download.
```text
要执行run python scripts/setup
```
问题4：Group(s) not found: ui (via --with) 或者 Group(s) not found: local (via --with)
```text
poetry install --extras ui
```
conda报错,比如conda update conda时报错CondaHTTPError: HTTP 000 CONNECTION FAILED for url <https://repo.anaconda.com/pkgs/r/win-64/repodata.json>
解决办法
```text
以下方法均可尝试
执行以下命令
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/
conda config --append channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/fastai/
conda config --append channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/
conda config --append channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/bioconda/
 

conda config --set show_channel_urls yes

再找到.condarc文件，替换为以下内容。
channels:
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/Paddle/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/fastai/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/bioconda/
show_channel_urls: true
ssl_verify: false

如何还不行，可以尝试关掉梯子或者所有的https改成http。
```