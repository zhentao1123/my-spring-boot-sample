package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	static final String basePackage = "com.example.demo.web";
	
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
			//.groupName("MyGroup")
			//.genericModelSubstitutes(DeferredResult.class)
            //.useDefaultResponseMessages(false)
            //.forCodeGeneration(true)
            //.pathMapping("/springboot")
			.select()
			//.apis(RequestHandlerSelectors.basePackage(basePackage))//api过滤，匹配包
			//.apis(RequestHandlerSelectors.withClassAnnotßation(Api.class))//api过滤，匹配类注解
			.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//api过滤，匹配方法注解
			//.paths(PathSelectors.any())
			//.paths(Predicates.or(PathSelectors.regex("/users/.*")))//api过滤，路径方式，匹配路径
			//.paths(Predicates.not(PathSelectors.regex("/users/.*")))//api过滤，路径方式，去除路径
			.build()
			//.directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class) //替代设置，未发现实际用途
			//.directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
			.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API标题位")
                .description("API描述位")
                .termsOfServiceUrl("服务URL的规范描述")
                .contact(new Contact("接口联系人", "联系人网站", "联系人邮箱"))
                .version("1.0")
                .build();
    }
}
