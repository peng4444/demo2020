package cn.pbj.demo2020.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @pClassName: SwaggerConfig
 * @author: pengbingjiang
 * @create: 2020/12/15 12:47
 * @description: TODO
 */
@Configuration
//开启swagger注解
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.pbj.demo2020.ssm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot使用Swagger构建api文档")
                .description("简单优雅的 restfun 风格 https://icode.blog.csdn.net")
                .termsOfServiceUrl("https://icode.blog.csdn.net")
                .version("1.0")
                .build();
    }
}
