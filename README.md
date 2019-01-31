# springboot-dubbo
项目主要是基于spring-boot框架搭建
# parent版本控制
* project-version: 0.0.1-SNAPSHOT
* spring-boot: 2.0.5.RELEASE
* dubbo: 2.6.5
* dubbo.springboot.starter: 0.2.0
* dubbo.spring.context.support: 1.0.2
* zookeeper: 3.4.6
# dubbo
dubbo示例，主要包括了api、provider、consumer三个工程，注册中心用zk，简单搭建了基于dubbo默认单一协议的微服务框架。本地搭建zk环境并运行。  
分别执行provider、consumer两个工程下的DubboSpringBootProvider、DubboSpringBootConsumer就能实现简单的微服务调用模式。  
因为consumer是用maven的maven-archetype-quitstart模板创建的model，所以就没有引入web.xml文件，不能直接运行run server启动consumer。
# httpclient
在spring-boot的httpclient基础包上，新建了一套httpclient工具，主要是控制http连接池，以及设定一些http请求属性。  
# spring-boot
spring-boot(SpringBootApplication)启动类默认自动扫描同包或子包路径，如果是其他包或顶级包，需用注解@ComponentScan显示配置扫描路径  
spring-boot-test，在做单元测试的时候，需注意注解@SpringBootTest配置相应的bean class
