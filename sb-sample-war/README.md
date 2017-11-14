## Spring Boot 演示之 War文件打包

#### 说明
>演示将Spring Boot应用打包成War文件

#### 环境
> * jdk1.8 
> * spring boot 1.5.8.RELEASE

#### 使用

> 1. 主要配置在pom.xml。注意去掉原来的内嵌tomcat，及加入spring-boot-starter-tomcat依赖。而后正常maven执行即可。
> 2. 插件spring-boot-maven-plugin可以对打包过程进行配置，如设置包名。

#### 实践的思考
> 1. War形式发布，原配置中Server部分应该都无效了，包括context-path，可以移除。



