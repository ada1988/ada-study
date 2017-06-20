学习案例
===================================  
  本人工作之余，记录的代码片段\<h1\>
    
ada-study-cache （ 本地、分布式、HTTP缓存） 
-----------------------------------   
###  缓存学习（堆内缓存、分布式缓存、http缓存）
  ### 堆内缓存：  
  基于spring-boot框架的Caching注解、MapDb框架（MapDB是一个快速、易用的嵌入式Java数据库引擎，它提供了基于磁盘或者堆外(off-heap允许Java直接操作内存空间, 类似于C的malloc和free)存储的并发的Maps、Sets、Queues）
  ### 分布式缓存：
  搜狐cachecloud以及redisson（redis官网推荐的java语言实现分布式锁的项目）
  ### HTTP缓存：  
  基于Filter，拦截对应地址，向response中设置header信息（最近修改时间），通过设置的header信息（最近修改时间），判断浏览器页面是否失效，并返回对应响应码：304（未失效）。
  



