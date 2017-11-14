## Spring Boot 演示之 Spring Data Repository

#### 说明
>演示Spring Data Repository

#### 环境
> 1. jdk1.8 
> 2. spring boot 1.5.8.RELEASE

#### 实践的思考
1. Spring Data Repository可以通过数据实体生成工具生成表对应的实体，可以通过继承Repository接口生成数据访问接口（不需要写实现类，装载时Spring自己实现）。生成单表访问对象再适合不过了。

2. Spring Data Repository本身支持数据对象1对1或1对多的关联配置，且可以通过特定的语句实现级联查询。但这引入了额外的学习和配置成本。级联查询还是JdbcTemplant+native sql方式比较灵活高效。

3. 事务方面JPA的事务管理器可以同时管理JPA和JdbcTemplate，配置相同的数据源即可。

