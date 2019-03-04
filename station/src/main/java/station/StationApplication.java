package station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reading.Reading;
import station.sendservice.SendService;
import station.sendservice.StationReadings;

import java.io.IOException;
import java.util.List;


@SpringBootApplication(scanBasePackages = {"station"})
@EnableScheduling
public class StationApplication {
    private static final Logger logger = LoggerFactory.getLogger(StationApplication.class);

    @Autowired
    private Station station;

    @Autowired
    private SendService sendService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(StationApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Scheduled(fixedRateString ="${station.reading-interval-secs:60000}", initialDelay = 0)
    public void collectAndReport(){

        List<Reading> readings = station.readAllSensors();

        try {
            sendService.send(new StationReadings(station.getUUID(), readings));
        }catch (IOException ioe){
            logger.error("Exception occurred sending readings", ioe);
        }
    }
}
