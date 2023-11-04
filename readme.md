# 素材管理系统

- 当前版本：v2023.5-1
- 后台代码版本：v0.2.1-alpha
- 前台代码版本：v0.2.0-alpha

一个用来管理本地素材的系统，目前处于开发阶段，所有发布版本都属于 alpha 版本。

> 如果需要导入已有资源，可以查看本文的**导入已有资源**。

## 获取方式

本系统是一个基于 springboot 和 antd-pro 的 web 系统，可以使用以下方法获取本系统：

1. 登录github，在actions页面下载release分支的最新制品。
2. 拉取代码到本地，自行打包。
3. 运行本工程下的 `package.sh` 。

如果使用第三种方式，首次使用前需要先拉取前台代码然后安装依赖，再运行 `package.sh` 。假如你想在 `c:/users/documents/media/code` 目录下运行 `package.sh` ，则 `code` 最终目录的结构如下。打包命令就是 `sh package.sh 要打包的分支名` 。**只能打包两个仓库都有的分支**，这个分支通常时发布分支或者 `dev` 分支或者主分支。

> **注意事项**：
> 运行 `package.sh` 需要安装 git-bash 和 maven 。 git-bash 可以用其它 shell 代替。

```
- code/ 打包目录。
    - media_ui/ 前台代码。
    - media/ 后台代码。
    - node_modules/ 前台依赖。
    - package.sh 打包脚本。
```

`package.sh` 会做如下事情：

1. 拉取代码，并将前后台发发行说明复制到静态文件目录。
2. 在配置文件中设置代码版本。次版本可以通过后台请求获取，展示在页面右上角。
3. 编译打包。此过程需要提前安装前台依赖。
4. 解压应用包，将其中的依赖复制到部署目录并生成启动脚本。

以上步骤完成后，会得到下面的目录结构：

```
- code/ 打包目录。
    - app/ 部署目录。
        - lib_maven 通过 maven 引入的依赖。
        - lib_module 通过 maven module 引入的依赖，也就是项目代码。
        - cli-分支名-日期.jar 命令行 jar 包。
        - media-分支名-日期.jar 应用 jar 包。
        - start-media.bat 应用启动脚本。
        - LoadDirectory.bat 加载目录下的资源。
        - LoadSingleFile.bat 按文件加载资源。
    - media_ui/ 前台代码。
    - media/ 后台代码。
    - node_modules/ 前台依赖。
    - package.sh 打包脚本。
```

app 目录就是最终的应用部署目录，可以理解为应用安装目录。

## 启动和部署

### 运行环境

如果拉代码后在本地启动，则需要 node14 和 java8+。注意：**前台的 ant-design-pro 版本是 4.5.0，因此不兼容高版本 node。我本地开发用的是
node14，如果用 node18 运行，则会抛出异常
**。启动后台只需要 java8+。如果你使用的是 release 工作流的产物，则只需要 java8+ 。如果本地没有安装 jdk，可以[点击下载openjdk8](https://download.java.net/openjdk/jdk8u42/ri/openjdk-8u42-b03-windows-i586-14_jul_2022.zip)。

### 检查是否安装jdk

首先打开命令行，方式是按 <kbd>win</kbd> + <kbd>r</kbd>，然后输入 cmd：

![1683383634877](./images/003_打开命令行.png)

在弹框中输入 `java -version`，如果显示和下面相似的内容，则说明已安装 java，可以跳到部署章节。**注意，版本号会随项目进度变动，且不是中文
**。

![1683384142383](./images/004_查看是否安装java.png)

如果运行结果是下图，则说明没有安装 java，可以[点击下载jdk8](https://download.java.net/openjdk/jdk8u42/ri/openjdk-8u42-b03-windows-i586-14_jul_2022.zip)，然后参考以下内容进行部署。

![1683384179369](./images/005_未安装java.png)

下载 jdk8 后，解压后得到以下内容：

![1683384393790](./images/006_openjdk8.png)

这个目录很长，我建议是将最里面的 `java-se-8u42-ri` 挪出来，比如挪到 `c:/users/你的用户名/downloads/media`下，然后改名 `openjdk8` ，之后将制品也下载到 `c:/users/你的用户名/downloads` 下面，然后你会得到下面的内容：

![1683384701177](./images/007_工作目录.png)

在这里新建一个文本文件，将下面的内容复制进去，然后将扩展名改为bat。

```bat
call openjdk8\jre\bin\java -Dspring.profiles.active=prd -jar media-2023.5-1.jar
call cmd \k
```

如果你的文件名是 新建文本文档.txt，则改完后应该是 `新建文本文档.bat` 。如果你的文件名是 `新建文本文档` 而不是 `新建文本文档.txt` ，请点击菜单栏的查看，然后勾选红线上面的框：

![1683385163820](./images/008_展示扩展名.png)

修改完成后，双击 `新建文本文档.bat` 就可以启动系统了。启动后你会看到多了一个 `data` 目录和 `logs` 目录，`data` 目录是存放系统数据的地方，最好不要动这个目录和里面的文件。`logs` 目录是日志目录，里面会保存两个月的日志文件，超过两个月的日志文件会自动删除。

### 部署

下载 release 工作流的产物后，通过 `java -Dspring.profiles.active=prd -jar media-版本号.jar` 启动工程，启动后会多出一个 `data` 目录和 `logs` 目录，前者保存系统数据，最好不要动里面的文件，后者保存系统日志，60天后自动删除。启动后打开浏览器访问 `http://localhost:9001/api` 即可。如果是第一次启动，则会报错，此时需要访问 `http://localhost:9001/api/h2`，打开后会看到以下界面，在 password 栏输入 media.h2 后点击 connect 进入控制台。

![](./images/001_h2登录页面.png)

进入控制台后，将后台仓库中的 `sql/initialize/media.sql` 复制到输入框 1，然后点击 2 run，之后就可以在 3 里看到运行结果。运行 sql 可以点 run 页可以点 run selected。

![1683382551593](./images/002_控制台.png)

如果系统升级到下个版本后有数据库变动，则会将变动语句放在后台仓库的 `sql/update/版本号`，同时修改 `sql/initialize/media.sql` 中的建表语句。如果版本升级没有修改数据库，则不存在目录 `sql/update/版本号` 。

## 版本发布

本系统代码仓库采用常规发布，每一个需求都会从主分支开一个开发分支 `feature-2023.7-1` ，命名规则是 `feature-月份-需求序号` 。比如 `feature-2023.7-1`
就表七月第一个需求。如果是修复线上 bug，则分支名为 `fix-2023.7-1`，表示七月第一个线上 bug。

需求开发完成后，会合并到开发分支 `dev` ，测试通过后会合并到发布分支 `release` 。这两个分支都配了工作流。发布分支测试通过后就会合并到主分支。目前配置的工作流打包出来的文件名是 `media-分支名.jar` 和 `cli-分支名.jar` 。如果使用发布分支打包，则制品中会包含版本号。如果用主分支打包，则制品中不包含版本号，但是可以手动修改文件名，在文件名中加入版本号。

目前系统使用的版本号是[语义化版本](https://semver.org/lang/zh-CN/)。前后台代码仓库分别有自己的版本号，工作流打包出来的制品也有自己的版本号和发行说明。前台代码看到的版本号配置在后台的 `application.properties` 中。

### 版本升级

按以下步骤升级：

1. 备份 `data` 目录。
2. 运行后台仓库的 `sql/update/版本号` 中的 sql。
3. 启动。
