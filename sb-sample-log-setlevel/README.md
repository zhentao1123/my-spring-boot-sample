## Spring Boot 演示之 动态设置日志级别

#### 概要
演示Spring Boot 动态设置日志级别

#### 环境
* jdk1.8 
* spring boot 1.5.8.RELEASE

#### 说明
1. Spring Boot 1.5.x中引入的一个新的控制端点：/loggers
2. 准备：引入``spring-boot-starter-actuator``，设置访问权限或者关闭安全检查``management.security.enabled=false``。
3. 访问``/loggers`` 可以查看当前项目中各包目录的日志输出级别，访问如``/loggers/com.example.demo``地址可以查看具体包目录的日志输出级别。
4. POST方式请求如``/loggers/com.example.demo``地址，并设置RequestBosy内容如``{"configuredLevel":"DEBUG"}``,可以在不重启项目的情况下动态修改日志输出级别。

#### 实践
1. 生成环境还是给``management``做安全设置为好。


