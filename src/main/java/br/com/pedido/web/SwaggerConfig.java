package br.com.pedido.web;

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
 * Configuração do Swagger para consultar a API através de um browser e para gerar API para o FRONT
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static  final String TITLE="PEDIDO API";
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("br.com.pedido.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
	
    private ApiInfo apiInfo() {
        ApiInfo apiInfos = new ApiInfoBuilder()
                .title(TITLE)
                .build();
        return apiInfos;
    }
}
