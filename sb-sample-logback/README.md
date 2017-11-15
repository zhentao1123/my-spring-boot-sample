## Spring Boot 演示之 logback

#### 说明
>演示Spring Boot 中配置logback

#### 环境
> * jdk1.8 
> * spring boot 1.5.8.RELEASE

#### 使用
> * 配置logging.config=classpath:my-logback.xml即可，配置文件不能用logback.xml有冲突

#### 实践的思考
> 1. 更多要注意的是log本身的实际配置，用<springProfile/>标签为不同的profile区别配置。