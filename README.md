
# Spring Cloud 搭建范例

## 前置要求

JDK: 1.8+

Maven: 3.2+

## 工程目录结构

分为几个独立的子目录，并不是子模块，可以分别构建和运行。这样是考虑到分布式的微服务本身就分为多个实体，可以运行在不同物理（或虚拟的）机上。

每个独立的目录是一个project，采用maven作为构建工具，可以导入到IDE中，也可以使用文本编辑器直接编辑代码后使用mvn命令构建运行。独立的project的说明见其目录内的README.md。

- config-server-example 配置中心的范例，以本地目录作为配置的存放仓库
- config-server-repo 配置中心的配置文件存储仓库
- eureka-server-example 注册中心的范例，以便客户端注册或获取spring web REST服务的实例
- simple-microservice 一个服务实例，使用到了注册中心及配置中心，对外提供REST服务以调用
- simple-facadeservice 靠近前端的门面服务，演示与注册中心的交互及调用其他服务

## 实体之间的关系

![](https://github.com/fengbiaoshan/publicdirectory/blob/master/spring-cloud-example-images/spring-cloud-example-relation.jpg?raw=true)



