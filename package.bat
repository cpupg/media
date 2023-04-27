call git pull
call git log -1 --all
call mvn clean
call mvn -DskipTests=true package
cmd /k