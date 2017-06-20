学习案例
===================================  
  本人工作之余，记录的代码片段\<h1\>
    
1、ada-study-cache （ 本地、分布式、HTTP缓存） 
-----------------------------------   
####  缓存学习（本地缓存、分布式缓存、http缓存）
  #####  本地缓存：  
  基于spring-boot框架的Caching注解、MapDb框架（MapDB是一个快速、易用的嵌入式Java数据库引擎，它提供了基于磁盘或者堆外(off-heap允许Java直接操作内存空间, 类似于C的malloc和free)存储的并发的Maps、Sets、Queues）
  ##### 分布式缓存：
  搜狐cachecloud以及redisson（redis官网推荐的java语言实现分布式锁的项目）
  ##### HTTP缓存：  
  基于Filter，拦截对应地址，向response中设置header信息（最近修改时间），通过设置的header信息（最近修改时间），判断浏览器页面是否失效，并返回对应响应码：304（未失效）。
  
  
2、ada-study-database-change （ 基于alibaba-canal框架，实现数据库监听） 
-----------------------------------   
####  监听者模式实现数据监听

 监听产品数据、库存数据变动，基于rabbitMQ消息系统，发送变动消息，有订阅者订阅变化。
 
 ##### canal框架 问题
 
 被监听数据库与canalServer问题：如果canalServer停止运行的一段时间，数据库中表结构变化、删除表前修改了数据，canalServer启动后狂暴错。<br />
 原因：获取修改数据时，首先获取表结构信息，由于删除了表，导致无法获取，所以报错。<br />
 
 ##### canal框架 问题 解决方案，修改源码：跳过、不处理表结构变化<br />
 
修改类：com.alibaba.otter.canal.parse.inbound.mysql.dbsync.LogEventConvert<br />
###### 1处：大概444行左右，在如下代码前，添加一行判断<br />

if (tableMeta != null && columnInfo.length > tableMeta.getFileds().size())<br />
添加代码：<br />
   //漏洞修复： 返回空数据，客户端做处理，服务端不做任何处理 {ada bug}        <br />                          ：
 if(tableMeta.getFullName().equals("大爷"))          <br />                        
 		return false;                                              <br />       
    
###### 2处:大概687行左右，将代码修改为如下内容：<br />

   if (StringUtils.contains(message, "errorNumber=1146") && StringUtils.contains(message, "doesn't exist")) {<br />
       //漏洞修复： 返回空数据，客户端做处理，服务端不做任何处理 {ada bug}<br />
        return new TableMeta("大爷", new ArrayList<TableMeta.FieldMeta>());<br />
   }<br />
 



