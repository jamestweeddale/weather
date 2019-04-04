package server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Profile("dev")
public class DevConfig {

    @Configuration
    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost")
                    .allowedMethods("PUT", "GET","POST")
                    .allowCredentials(false).maxAge(3600);
        }
    }

}
