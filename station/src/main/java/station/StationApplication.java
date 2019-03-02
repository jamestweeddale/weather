package station;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"station"})
@EnableScheduling
public class StationApplication{

    public static void main(String[] args) {
        new SpringApplicationBuilder(StationApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
