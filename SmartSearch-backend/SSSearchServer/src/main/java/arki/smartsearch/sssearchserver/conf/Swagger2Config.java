package arki.smartsearch.sssearchserver.conf;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("main")
                .select()
                .apis(RequestHandlerSelectors.basePackage("arki.smartsearch.sssearchserver.controllor"))
                .build();// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Wei Wang", "http://wwww.arkilovesprogramming.com/联系人访问链接", "betaweiwang@gmail.com");
        return new ApiInfoBuilder()
                .contact(
                        contact
                )
                .title("Smartsearch API documentation")
                .description("Smartsearch API documentation")
                .version("1.0")
                .build();
    }
}
