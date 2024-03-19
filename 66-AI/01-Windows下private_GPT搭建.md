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

工作负荷-通用Windows平台开发，以及右侧的C++(v142)通用Windows平台工具

单个组件-用于Windows的C++ CMake工具

### 安装MinGW
下载地址：https://sourceforge.net/projects/mingw-w64

安装后打开MinGW Installation Manager

勾选mingw32-gcc-g++，点击Installation->Apply Changes

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
mkdir D:\privateGPT
cd D:\privateGPT
git clone https://github.com/imartinez/privateGPT
cd privateGPT
```

### 配置依赖并安装模型
```shell
pip install poetry
# 新版本使用poetry install --extras ui
poetry install --with ui, local 
poetry run python scripts/setup
```

### 启动本地服务
```shell
set PGPT_PROFILES=local
python -m private_gpt
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