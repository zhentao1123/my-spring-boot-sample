## Spring Boot 演示之 Spring Data Rest

#### 说明
>演示Spring Data Rest

#### 环境
> * jdk1.8 
> * spring boot 1.5.8.RELEASE
> * h2database

#### 使用

> 访问服务根目录即以Json格式返回所有暴露的Rest接口地址。如：
> ```json
> {
  "_links" : {
    "cities" : {
      "href" : "http://localhost:8080/api/cities{?page,size,sort}",
      "templated" : true
    },
    "hotels" : {
      "href" : "http://localhost:8080/api/hotels{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile"
    }
  }
}
> ```

#### 实践的思考
> 1. Spring Data Rest，接口通过继承Repository基础上简单配置@RepositoryRestResource注解，就可以将Spring Data暴露基于Rest的访问接口。特别适用于不希望暴露数据库连接信息又希望能对数据表（集合）进行操作的场合，比如访问更新缓存数据库。

> 2. 数据毕竟是敏感的，实际使用还是需要加安全控制。


