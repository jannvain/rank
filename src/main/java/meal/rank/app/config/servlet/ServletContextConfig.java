package meal.rank.app.config.servlet;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * Spring MVC config for the servlet context in the application.
 *
 * The beans of this context are only visible inside the servlet context.
 *
 */
@Configuration
@EnableWebMvc
// @Import({ SecurityConfig.class })
@ComponentScan("meal.rank.app.controllers")
public class ServletContextConfig extends WebMvcConfigurerAdapter {

/*	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }
  */  
	

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/api/user/**").addResourceLocations("/api/user/");
        registry.addResourceHandler("/api/meal/**").addResourceLocations("/api/meal/");
        registry.addResourceHandler("/api/mymeals/**").addResourceLocations("/api/mymeals/");

        registry.addResourceHandler("/mealimages/xs/**").addResourceLocations("file:/Users/vainio6/rankme/pic/xs/");
        registry.addResourceHandler("/mealimages/lg/**").addResourceLocations("file:/Users/vainio6/rankme/pic/lg/");

//        registry.addResourceHandler("img/**").addResourceLocations("resources/img", "/resources/img", "/mealrank/resources/img");
       
   //     registry.addResourceHandler("resources/**").addResourceLocations("/resources/", "/mealrank/resources/");

    }
    
    
    @Bean
    public MultipartResolver multipartResolver() {
    	return new StandardServletMultipartResolver();
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(500000);
//        return multipartResolver;
    }    
    
}
