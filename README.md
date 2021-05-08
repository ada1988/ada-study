学习案例
===================================  
  本人工作之余，记录的代码片段\<h1\>
    
1、ada-study-cache （ 本地、分布式、HTTP缓存） 
-----------------------------------   
####  缓存学习（本地缓存、分布式缓存、HTTP缓存）
##### 本地缓存：  <br />
  
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
 

2、ada-pay-parent （ 基于Feign框架，封装三方银行接口对接） 
----------------------------------- 
####  项目结构
######	ada-pay-common  公共类封装（开启支付注解、泛型封装、feign动态地址、feign日志）
######	ada-pay-pab	平安银行接口对接（实现IBasicsService接口处理前置、后置封装；基于接口文档创建请求实体、响应实体、接口实现）
######	ada-pay-hbb	河北银行接口对接（实现IBasicsService接口处理前置、后置封装；基于接口文档创建请求实体、响应实体、接口实现）
######	ada-pay-demo   使用案例（pom添加依赖;启动类开启@EnableAdaPay注解;注入实现的接口，调用父类实现的requestApi方法）

###### 超简单的三方接口对接：<br />
	前期仅需一人对接加密、解密等事项，其余繁杂的三方接口对接，可以交于初级工程师完成
	前期实现仅需实现IBasicsService接口：
		/**
		 * 服务请求:前置处理。  如获取公钥、私有、数据加密等
		 * @param <Req>
		 * @param req
		 * @return
		 */
		public <Req> Map<String,Object> beforeRequestHandler(Req req);
	
		/**
		 * 服务请求:后置处理。如解密、转换返回实体等
		 * @param <Resp>
		 * @param resp
		 * @param clazz
		 * @return
		 */
		public <Resp> RespBase<Resp> afterRequestHandler(String result,Class<Resp> clazz);
		

