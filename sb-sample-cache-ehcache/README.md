## Spring Boot 演示之 cache (Ehcache 2.x)

#### 说明
>演示cache (Ehcache 2.x)

#### 环境
> 1. jdk1.8 
> 2. spring boot 1.5.8.RELEASE
> 3. h2database
> 4. ehcache 2.10.4

#### 实践的思考
1. @Cacheable之外的注释如@CacheConfig、@CachePut等谨慎使用
2. 在ehcache.xml中对具体业务等缓存做适当的配置

