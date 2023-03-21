call git checkout pre-release
call git checkout .
call git pull
call git branch -vv
call git log --oneline -5
call mvn clean
call mvn -DskipTests=true package
cmd /k