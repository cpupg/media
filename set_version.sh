if [[ $# -ne 1 ]]; then
    echo "请输入版本号";
    exit 1;
fi

SUB_MODULE="common service web application dataaccess cli config";
CLASSPATH="target/classes;target/dependency/dom4j-2.1.4.jar";
MAIN="com.wrotecode.maven.wrapper.SetVersion";
VERSION=$1;
ENCODING="-Dfile.encoding=UTF-8";

PROJECT_DIR=$(pwd);
echo "项目目录:$PROJECT_DIR";

export CLASSPATH=$CLASSPATH;


echo $(java -version);
echo "==========================================================================";
echo $(mvn -version);
echo "==========================================================================";


cd wrapper;
echo "开始编译";
mvn compile;
echo "复制依赖";
mvn dependency:copy-dependencies

echo "设置父工程版本-------------";
java $ENCODING $MAIN $PROJECT_DIR/pom.xml $VERSION current;

for dir in $SUB_MODULE
do
  echo "修改${dir}版本-------------";
  java $ENCODING $MAIN $PROJECT_DIR/$dir/pom.xml $VERSION parent;
done
