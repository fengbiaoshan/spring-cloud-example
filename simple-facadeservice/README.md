
# FacadeService

## 前置要求

JDK: 1.8+

Maven: 3.2+

## 配置及启动

1. 使用`mvn package`进行构建，在target中会生成jar包
2. `java -jar target/simple-facadeservice-***.jar`启动，注意\*\*\*要换成对应的版本号

## 注意

例子中展示了两种方式来调用其他REST服务：

- ribbon + restTemplate
- feign

具体请看FacadeApp.java中的实现。

应用了Hystrix断路器做服务降级处理。

feign的使用，注意其默认注册的spring bean的名字是类的全限定名及`{引用的appname}+ FeignClient`，可用qualifier指定名称
 



