
# Spring Cloud Config

## 前置要求

JDK: 1.8+

Maven: 3.2+

## 配置及启动

1. 更改src/main/resources中的application.yml文件，将仓库的地址指向本地目录。默认没有使用带有版本控制的仓库，若要使用git来管理以往的配置版本，可使用文件中注释的那一部分进行配置指向git仓库，指向的位置必须被git管理（有.git文件夹）
2. 使用`mvn package`进行构建，在target中会生成jar包
3. `java -jar target/config-server-example-***.jar`启动，注意\*\*\*要换成对应的版本号

## 仓库及配置说明

config server的仓库有很多种形式，推荐使用git仓库，在仓库中的application.yml或application.properties是针对所有客户端都有效的，还可以存放{appname}.yml或{appname}.properties的文件，其中的配置只针对spring.application.name为{appname}的客户端有效。使用远程配置config的客户端的配置的优先级默认是config server中的{appname}.yml > server中的applicaiton.yml > 本地的application.yml, 对应的properties和yml同级。若要让远程不覆盖本地已有的配置需要额外设置。
 



