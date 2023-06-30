UI="git@github.com:wrote-code/media-ui.git";
SERVER="git@github.com:wrote-code/media.git";
UI_DIR="media-ui";
SERVER_DIR="media";
WORK_DIR=$(pwd);

function log() {
    echo $*;
}

function hr() {
    cd $WORK_DIR;
    echo "=====>>>$1<<<=====";
}

function runStatus() {
    echo $1;
    if [[ $1 -ne 0 ]]; then
        echo "执行错误:$1";
    fi
}

if [[ $# -ne 1 ]]; then
    echo "请输入分支名称";
    exit 1;
fi

# 分支名称
branch=$1;
hr "检查当前工作目录是否正常";
if [[ -e $UI_DIR ]]; then
    log "删除ui目录";
    rm -rfv $UI_DIR;
fi
if [[ -e $SERVER_DIR ]]; then
    log "删除server目录";
    rm -rfv $SERVER_DIR;
fi

log "分支名称:$branch";

hr "拉取前台代码";
git clone -b $branch --depth 1 $UI;
runStatus $?;

hr "拉取后台代码";
git clone -b $branch --depth 1 $SERVER;
runStatus $?;
log "创建静态文件目录";
mkdir -p $SERVER_DIR/application/src/main/resources/static
runStatus $?;

hr "复制发行说明";
cp -v $SERVER_DIR/releasenote.html $SERVER_DIR/application/src/main/resources/static
runStatus $?;
cp -v $UI_DIR/releasenote-ui.html $SERVER_DIR/application/src/main/resources/static
runStatus $?;

hr "设置代码版本";
cd $SERVER_DIR;
runStatus $?;
touch application/src/main/resources/static/ui-$(git rev-parse $branch)
runStatus $?;
touch application/src/main/resources/static/server-$(git rev-parse $branch)
runStatus $?;
touch cli/src/main/resources/cli-$(git rev-parse $branch)
runStatus $?;

hr "编译前台代码";
mv node_modules $UI_DIR
runStatus $?;
cd $UI_DIR;
npm run build
if [[ $? -ne 0 ]]; then
    log "前台编译失败";
    mv $UI_DIR/node_modules .
    exit 1;
fi
log "编译完成，挪走node_modules";
mv node_modules ../
runStatus $?;

hr "编译后台代码";
cd $SERVER_DIR;
log "复制前台产物";
cp -rv ../$UI_DIR/dist/* application/src/main/resources/static
runStatus $?;
log "编译";
mvn -DskipTests=true package
runStatus $?;

hr "移动jar包";
mv -v $SERVER_DIR/application/target/*.jar ./media-$branch-$(date '+%Y.%m.%d').jar
runStatus $?;
mv -v $SERVER_DIR/cli/target/*.jar ./cli-$branch-$(date '+%Y.%m.%d').jar
runStatus $?;
