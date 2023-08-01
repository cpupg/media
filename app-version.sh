# 通常，只有三个分支会由打包行为，即dev,release-*,master
# 可以自行修改改文件中的版本号为想要的版本
echo "main=v$(git branch --show-current)" > "config/src/main/resources/media-application.properties"
