# 打包脚本，可以在git-bash或其它linux shell中运行
# 如果你将git安装目录下的usr/bin加入到环境变量PATH中，你也可以直接在cmd中运行sh package.sh dev
# 来打包
UI="git@github.com:wrote-code/media-ui.git";
SERVER="git@github.com:wrote-code/media.git";
UI_DIR="media-ui";
SERVER_DIR="media";
WORK_DIR=$(pwd);
LOG_FILE=package.log;
echo $WORK_DIR;
function log() {
    echo "[$(date +'%Y-%m-%d %H:%M:%S')] $*";
}

function hr() {
    cd $WORK_DIR;
    echo "=====>>>$1<<<=====";
}

function runStatus() {
    log "执行结果 -> $1";
    if [[ $1 -ne 0 ]]; then
         log "执行错误 -> $1";
         exit $1;
    fi
}

if [[ $# -ne 1 ]]; then
    log "请输入分支名称";
    exit 1;
fi

function execute() {
  log $1;
  $1
  runStatus $?;
}

# 分支名称
branch=$1;
hr "检查当前工作目录是否正常";
if [[ -e $UI_DIR ]]; then
    log "删除ui目录";
    rm -rfv $UI_DIR;
fi
if [[ -e $SERVER_DIR ]]; then
    log "删除server目录";
    rm -rfv $SERVER_DIR $LOG_FILE;
fi

log "分支名称:$branch";

hr "拉取前台代码";
execute "git clone -b $branch --depth 1 $UI";

hr "拉取后台代码";
execute "git clone -b $branch --depth 1 $SERVER";
log "创建静态文件目录";
execute "mkdir -p $SERVER_DIR/application/src/main/resources/static";

hr "复制发行说明";
execute "cp -v $SERVER_DIR/releasenote.html $SERVER_DIR/application/src/main/resources/static";
execute "cp -v $UI_DIR/releasenote-ui.html $SERVER_DIR/application/src/main/resources/static";

hr "设置后台代码版本";
execute "cd $SERVER_DIR";
execute "touch application/src/main/resources/static/server-$(git rev-parse $branch)";
execute "touch cli/src/main/resources/cli-$(git rev-parse $branch)";

hr "设置前台代码版本"
execute "cd $UI_DIR";
execute "touch $WORK_DIR/$SERVER_DIR/application/src/main/resources/static/ui-$(git rev-parse $branch)":

hr "编译前台代码";
execute "mv node_modules $UI_DIR";
execute "cd $UI_DIR";
echo "npm run build";
npm run build;
if [[ $? -ne 0 ]]; then
    log "前台编译失败";
    echo "mv $UI_DIR/node_modules .";
    mv $UI_DIR/node_modules .
    exit 1;
fi
log "编译完成，挪走node_modules";
echo "mv node_modules ../";
mv node_modules ../
runStatus $?;

hr "编译后台代码";
execute "cd $SERVER_DIR";
log "复制前台产物";
execute "cp -rv ../$UI_DIR/dist/* application/src/main/resources/static";
log "编译";
execute "mvn -DskipTests=true package";

hr "移动jar包";
execute "mv -v $SERVER_DIR/application/target/*.jar ./media-$branch-$(date '+%Y.%m.%d').jar";
execute "mv -v $SERVER_DIR/cli/target/*.jar ./cli-$branch-$(date '+%Y.%m.%d').jar";
