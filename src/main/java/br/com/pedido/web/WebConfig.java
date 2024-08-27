/**
 * 
 */
package br.com.pedido.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import br.com.pedido.interceptor.LoggerInterceptor;

/**
 * Configuração para ter o controle das características padrões do SPRING e override do LoggerInterceptor no mesmo
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    public WebConfig() {
        super();
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("**/*.css", "**/*.js", "**/*.map", "*.html", "swagger-ui.html")
				.addResourceLocations("classpath:META-INF/resources/").setCachePeriod(0);
		
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
    }
}
