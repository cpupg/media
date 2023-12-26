# 多媒体文件管理平台

一个以后台方式管理多媒体文件的平台，功能如下：
- 支持多种分类方式：标签分类、收藏分类、专辑分类。
- 支持通过命令行批量导入资源，导入时可以通过正则表达式来过滤文件，详情参考命令行帮助文档。
- 使用嵌入式数据库h2，只需要安装jre环境即可运行，不需要额外配置数据库

> **注意事项**
> 1. 如果需要导入已有资源，可以查看本文的**导入已有资源**。
> 2. 若需要自行打包编译需要安装jdk1.8+,node14.21.3,maven3.9.2。安装node后还需要安装yarn，详情参考下面的打包章节。
> 3. 目前只支持h2数据库，不兼容mysql或其他数据库。

## 获取方式

本系统基于springboot和antd-pro，可以自行拉代码在本地启动，也可以拉代码后执行后台代码中的`package.sh`脚本打包后用`java -jar`启动。使用
`package.sh`需要加分支名，也就是`sh package.sh dev`。`dev`分支相当于预发布分支，开发完成的功能会先合并到`dev`分支，然后再合并到`master`
分支。

## 使用package.sh手动打包

### 环境要求

为了运行`package.sh`，你首先要安装以下环境：
- node 14.21.3：前台代码不兼容最新版本的node，推荐使用[nvm](https://github.com/nvm-sh/nvm)管理node版本。windows下可以使用[nvm-windows]
  (https://github.com/coreybutler/nvm-windows)。
  - yarn：安装node后需要安装包管理工具yarn。
- maven 3.9.2：
- jdk 1.8+：推荐使用openjdk，oraclejdk需要登录才能下载。
- git-bash或其他shell，无版本要求。

### 打包过程

首先，你需要准备一个空白目录，假设目录名是`code`，将`package.sh`复制到`code`中然后手动克隆前台代码，运行`yarn install`安装依赖，安装完成后将`node_modules`移动到`code`，然后你的目录结构就是下面这样：

```
- code/ 打包目录。
    - media_ui/ 前台代码。
    - node_modules/ 前台依赖。
    - package.sh 打包脚本。
```

然后执行`package.sh`，执行的时候要加分支名称。如果你想使用稳定版，就用`master`分支，否则使用`dev`分支。**参数分支必须是在前后台代码仓库中都有的分支**。如果`package.sh`执行过程没有报错，则会得到下面的目录结构：

```
- code/ 打包目录。
    - app/ 部署目录。
        - lib_maven 通过 maven 引入的依赖。
        - lib_module 通过 maven module 引入的依赖，也就是项目代码。
        - cli-分支名-后台代码revision-日期.jar 命令行 jar 包。
        - media-分支名-后台代码revision-前台代码revision-日期.jar 应用 jar 包。
        - start-media.bat 应用启动脚本。
        - LoadDirectory.bat 加载目录下的资源。
        - LoadSingleFile.bat 按文件加载资源。
    - media_ui/ 前台代码。
    - media/ 后台代码。
    - node_modules/ 前台依赖。
    - package.sh 打包脚本。
```

app 目录就是最终的应用部署目录，可以理解为应用安装目录。

## 如何使用

如果你成功运行了`package.sh`，那么你可以直接按下面的方式启动。如果你是下载的现成产品，那么你只需要安装jre1.8即可，然后运行`start-media.
bat`就可以启动系统，启动后在浏览器访问`http://localhost:9001/api`就可以进入系统。
