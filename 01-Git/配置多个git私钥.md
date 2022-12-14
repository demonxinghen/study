# 如果多个私钥和公钥, 需要在.ssh目录下创建config文件, 如果没有可以手动创建
# Host 仓库网站的别名, 任意
# HostName 域名或IP:PORT
# User 仓库网站上的用户名
# IdentityFile 私钥的绝对路径
# PreferredAuthentications 认证方式

# 移除全局配置
```shell
git config --global --unset user.name
git config --global --unset user.email
git config --global --unset user.password
```
# 每个仓库都配置仓库级配置
```shell
git config --local user.name=xxx
git config --local user.email=xxx
```

添加配置
```text
Host gitlab.com
HostName www.gitlab.com
User xuhui3@yonyou.com
IdentityFile ~/.ssh/gitlab_rsa
PreferredAuthentications publickey

Host github.com
HostName www.github.com
User demonxinghen@126.com
IdentityFile ~/.ssh/github_rsa
PreferredAuthentications publickey
```