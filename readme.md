# 素材管理系统

一个用来管理本地素材的系统，目前处于开发阶段，所有发布版本都属于 alpha 版本。

## 获取方式

本系统是一个基于 springboot 和 antd-pro 的 web 系统，获取本系统有两种方式，第一种是拉代码到本地自己启动，第二种是在 actions 页面下载制品然后启动。如果拉代码到本地启动，则需要 jdk8 和 node14。在发布第一个 1.0.0 稳定版后会按实际需要逐步升级使用的框架。

### github actions

本系统前后台代码都配置了 github actions，每个仓库都配了两个工作流，在推送发布分支和主分支时会执行工作流。两个工作流简要说明如下：

- `release-build/release-package`：这两个工作流内容完全相同，只有文件名和工作流名不同。推送 `master` 或 `build-*` 和 `package-*` 分支时会执行。`build-*` 是前台的发布分支，`package-*` 是后台的发布分支。在功能开发完成后，会先将开发分支合并到发布分支，测试无误后再合并到 `master` 主分支。工作流产物是一个可执行 jar 包，jar 中包含编译好的前台产物，因此不需要 nginx 做代理。
- `npm-build` 和 `mvn-package` ：这两个工作流执行时间和 `release-build/release-package` 相同，单独打包前台代码和后台代码，前台产物需要通过 nginx 代理到后台。

**注意事项**：release 工作流打包的是最新的发布分支，因此每次开新发布分支时都要修改工作流并提交。假设现在前后台发布分支分别为 `build-0.2.0-alpha` 和 `package-0.2.1-alpha`，则工作流执行完成后，如果向前台发布分支提交了新代码，则后台仓库的发布产物会失效，因为后台执行工作流时用的前台代码不是最新的。反过来也一样。

## 启动和部署

#### 运行环境

如果拉代码后在本地启动，则需要 node14 和 java8+。注意：**前台的 ant-design-pro 版本是 4.5.0，因此不兼容高版本 node。我本地开发用的是 node14，如果用 node18 运行，则会抛出异常**。启动后台只需要 java8+。如果你使用的是 release 工作流的产物，则只需要 java8+ 。如果本地没有安装 jdk，可以[点击下载openjdk8](https://download.java.net/openjdk/jdk8u42/ri/openjdk-8u42-b03-windows-i586-14_jul_2022.zip)。

#### 检查是否安装jdk

首先打开命令行，方式是按 <kbd>win</kbd> + <kbd>r</kbd>，然后输入 cmd：

![1683383634877](./images/003_打开命令行.png)

在弹框中输入 `java -version`，如果显示和下面相似的内容，则说明已安装 java，可以跳到部署章节。**注意，版本号会随项目进度变动，且不是中文**。

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

#### 部署

下载 release 工作流的产物后，通过 `java -Dspring.profiles.active=prd -jar media-版本号.jar` 启动工程，启动后会多出一个 `data` 目录和 `logs` 目录，前者保存系统数据，最好不要动里面的文件，后者保存系统日志，60天后自动删除。启动后打开浏览器访问 `http://localhost:9001/api` 即可。如果是第一次启动，则会报错，此时需要访问 `http://localhost:9001/api/h2`，打开后会看到以下界面，在 password 栏输入 media.h2 后点击 connect 进入控制台。

![](./images/001_h2登录页面.png)

进入控制台后，将后台仓库中的 `sql/initialize/media.sql` 复制到输入框 1，然后点击 2 run，之后就可以在 3 里看到运行结果。运行 sql 可以点 run 页可以点 run selected。

![1683382551593](./images/002_控制台.png)

如果系统升级到下个版本后有数据库变动，则会将变动语句放在后台仓库的 `sql/update/版本号`，同时修改 `sql/initialize/media.sql` 中的建表语句。如果版本升级没有修改数据库，则不存在目录 `sql/update/版本号` 。

## 版本发布

本系统代码仓库采用常规发布，每一个需求都会从主分支开一个开发分支 `dev-202305-1` ，命名规则是 `dev-月份-需求序号` 。比如 `dev-202305-1` 就表示五月第一个需求。如果是修复线上 bug，则分支名为 `fix-202305-1`，表示五月第一个线上 bug。

需求开发完成后，会从主分支开一个发布分支，前台发布分支是 `build-下一个版本号` ，后台发布分支是 `package-下一个版本号` 。然后将开发分支合并到发布分支，测试无误后再合并到主分支，同时发布新版本。**注意：`release` 工作流的产物的版本号是后台版本号，不是前台版本号，也不是整体版本号**。整体版本号应该使用日历化版本，形式是 `v202305-1` 。最后一位表示序号，比如 `v202305-1` 表示 2023 年 5 月的第一个版本。由于目前没有研究出动态修改版本号的方案，大家可以下载制品后手动修改版本号。

### 版本号规则

代码版本遵循[语义化版本](https://semver.org/lang/zh-CN/)，制品版本遵循[日历化版本](https://calver.org/overview_zhcn.html)
。**注意：由于个人原因，目前编写的ci配置不能实现打包产物使用日历版本。**此外，打包时使用的前后台代码版本可以在jar包的 `BOOT-INF/classes/static/ui-version.txt` 和
`BOOT-INF/classes/static/server-version.txt` 中找到，其中的内容是 `git log -1` 的运行结果。系统启动后也可以直接通过浏览器访问该文件。 