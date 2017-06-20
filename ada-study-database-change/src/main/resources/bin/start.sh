#!/bin/bash 

#指定字符集、根路径、配置文件
current_path=`pwd`
case "`uname`" in
    Linux)
		bin_abs_path=$(readlink -f $(dirname $0))
		;;
	*)
		bin_abs_path=`cd $(dirname $0); pwd`
		;;
esac
base=${bin_abs_path}/..
echo base:$base
project_conf=$base/conf/application.properties
START_LOG=$base/logs/database-change.log
export LANG=en_US.UTF-8
export BASE=$base
echo conf_path  :  $project_conf

#服务是否允许启动
if [ -f $base/bin/process.pid ] ; then
	echo "found process.pid , Please run stop.sh first ,then start.sh" 2>&2
    exit 1
fi

if [ ! -d $base/logs ] ; then 
	mkdir -p $base/logs
fi

# 启动的日志文件  
if [ ! -f "$START_LOG" ]; then  
  #等于 mk 创建文件  
    touch $START_LOG  
fi 

## set java path
if [ -z "$JAVA" ] ; then
  JAVA=$(which java)
  else
  	echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.5) in your PATH." 2>&2
    exit 1
fi
#输出环境变量
echo java_home  :  $JAVA

#配置JVM
str=`file -L $JAVA | grep 64-bit`
if [ -n "$str" ]; then
	JAVA_OPTS="-server -Xms1024m -Xmx1526m -Xmn516m -XX:SurvivorRatio=2 -Xss228k -XX:-UseAdaptiveSizePolicy -XX:MaxTenuringThreshold=15 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:+HeapDumpOnOutOfMemoryError"
else
	JAVA_OPTS="-server -Xms1024m -Xmx516m -XX:NewSize=128m -XX:MaxNewSize=128m -XX:MaxPermSize=64m "
fi
#输出虚拟机配置
echo jvm_conf   :  $JAVA_OPTS

JAVA_OPTS=" $JAVA_OPTS -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
PROJECT_OPTS="-DappName=databse-change"
#判断是否存在配置文件
if [ -f $project_conf ]
then 
  cd $bin_abs_path
  $JAVA $JAVA_OPTS $PROJECT_OPTS -jar ../database-change.jar --spring.config.location=$project_conf >> $START_LOG 2>&1 &
  echo $! > $base/bin/process.pid 
else 
	echo "databse-change conf("$project_conf") is not exist,please create then first!"
fi
