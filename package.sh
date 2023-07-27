# 打包脚本，可以在git-bash或其它linux shell中运行
# 如果你将git安装目录下的usr/bin加入到环境变量PATH中，你也可以直接在cmd中运行sh package.sh dev
# 来打包
UI_URL="git@github.com:wrote-code/media-ui.git";
SERVER_URL="git@github.com:wrote-code/media.git";
UI_DIR="media-ui";
SERVER_DIR="media";
WORK_DIR=$(pwd);
APP_DIR=app;
LOG_FILE=$WORK_DIR/package.log;
# 加了-v参数的rm和mv命令日志写在这里
VERBOSE_LOG_FILE=verbose.log;
MODULE_LIST="common config dataaccess service web";
MODULE_LIB_DIR=$APP_DIR/lib_module;
MAVEN_LIB_DIR=$APP_DIR/lib_maven;
################################################################################
# 预定义函数
# 输出日志，同时在控制台和文件中展示
function log() {
    echo "[$(date +'%Y-%m-%d %H:%M:%S')] $*";
}

# 显示分割线
function hr() {
    cd $WORK_DIR;
    echo "===============>>>$1<<<===============";
}

function runStatus() {
    log "执行结果 -> $1";
    if [[ $1 -ne 0 ]]; then
         log "执行错误 -> $1";
         exit $1;
    fi
}
################################################################################

if [[ $# -ne 1 ]]; then
    log "请输入分支名称";
    exit 1;
fi

branch=$1;
hr "检查当前工作目录是否正常";
if [ -e $UI_DIR/node_modules ]; then
    log "存在node_modules目录，将改目录移动到父目录";
    mv -v $UI_DIR/node_modules $WORK_DIR;
    runStatus $?
fi

if [[ -e $UI_DIR ]]; then
    log "删除ui目录";
    rm -rfv $UI_DIR >> $VERBOSE_LOG_FILE;
    runStatus $?;
fi
if [[ -e $SERVER_DIR ]]; then
    log "删除server目录";
    rm -rfv $SERVER_DIR >> $VERBOSE_LOG_FILE;
    runStatus $?;
fi

if [[ -e $APP_DIR ]]; then
    log "删除app目录";
    rm -rfv $APP_DIR >> $VERBOSE_LOG_FILE;
fi

log "分支名称:$branch";

hr "拉取前台代码";
git clone -b $branch --depth 1 $UI_URL;
runStatus $?;

hr "拉取后台代码";
git clone -b $branch --depth 1 $SERVER_URL;
runStatus $?;
log "创建静态文件目录";
mkdir -p $SERVER_DIR/application/src/main/resources/static;
runStatus $?;

hr "复制发行说明";
cp -v $SERVER_DIR/releasenote.html $SERVER_DIR/application/src/main/resources/static;
runStatus $?;
cp -v $UI_DIR/releasenote-ui.html $SERVER_DIR/application/src/main/resources/static;
runStatus $?;

hr "设置后台代码版本";
cd $SERVER_DIR;
runStatus $?;
touch application/src/main/resources/static/server-$(git rev-parse $branch);
runStatus $?;
touch cli/src/main/resources/cli-$(git rev-parse $branch);
runStatus $?;

hr "设置前台代码版本"
cd $UI_DIR;
runStatus $?;
touch $WORK_DIR/$SERVER_DIR/application/src/main/resources/static/ui-$(git rev-parse $branch);
runStatus $?:

hr "设置系统版本"
cd $SERVER_DIR;
runStatus $?;
echo "main=v$(git branch --show-current)" > "config/src/main/resources/media-application.properties"

hr "编译前台代码";
mv -v node_modules $UI_DIR;
runStatus $?;
cd $UI_DIR;
runStatus $?;
# 日志文件不会显示进度条，同时控制台也不会显示进度条
npm run build >> $LOG_FILE;
if [[ $? -ne 0 ]]; then
    log "前台编译失败";
    # 前台编译失败要将依赖挪出去，否则下次运行会删除依赖
    mv -v $UI_DIR/node_modules .;
    exit 1;
fi
log "编译完成，挪走node_modules";
mv -v node_modules ../;
runStatus $?;

hr "编译后台代码";
cd $SERVER_DIR;
runStatus $?;
log "复制前台产物";
cp -rv ../$UI_DIR/dist/* application/src/main/resources/static >> $VERBOSE_LOG_FILE;
runStatus $?;
log "编译";
mvn -DskipTests=true package;
runStatus $?;

hr "移动jar包";
mv -v $SERVER_DIR/application/target/*.jar $APP_DIR/media-$branch-$(date '+%Y.%m.%d').jar;
runStatus $?;
mv -v $SERVER_DIR/cli/target/*.jar $APP_DIR/cli-$branch-$(date '+%Y.%m.%d').jar;
runStatus $?;

hr "设置cli依赖";
log "创建目录";
mkdir $APP_DIR;
mkdir $MODULE_LIB_DIR;
mkdir $MAVEN_LIB_DIR;
log "移动模块依赖";
for dir in $MODULE_LIST;do
    mv -v $SERVER_DIR/$dir/target/*.jar $MODULE_LIB_DIR;
done
log "解压jar包";
unzip $APP_DIR/media-$branch-$(date '+%Y.%m.%d').jar -d app >> $VERBOSE_LOG_FILE;
runStatus $?;

mv -v $APP_DIR/BOOT-INF/lib/* $MAVEN_LIB_DIR >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/BOOT-INF >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/META-INF >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/org >> $VERBOSE_LOG_FILE;
runStatus $?;
log "删除maven依赖目录中的模块依赖";
for f in $(ls $MODULE_LIB_DIR);do
    rm -v $MAVEN_LIB_DIR/$f;
    runStatus $?;
done

log "复制启动脚本";
cp -v $SERVER_DIR/bin/* $APP_DIR;

log "打包完成";
