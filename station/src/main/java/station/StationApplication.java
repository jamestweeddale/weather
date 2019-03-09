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
import reading.StationReadings;
import station.sendservice.SendService;

import java.io.File;
import java.nio.file.Files;
import java.time.ZonedDateTime;
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

    @Scheduled(fixedRateString = "${station.reading-interval-secs:60000}", initialDelay = 0)
    public void collectAndReport() {

        List<Reading> readings = station.readAllSensors();

        try {
            sendService.send(new StationReadings(station.getUUID(), readings));
        } catch (Exception e) {
            logger.error("Exception occurred sending readings", e);
        }
    }


    @Scheduled(fixedRateString = "${station.picture-interval-secs:60000}", initialDelay = 0)
    public void collectAndReportPicture() {
        logger.info("snapping a pic...");
        try {
            File picture = station.takePicture();

            sendService.send(station.getUUID(), ZonedDateTime.now(), picture);

            Files.delete(picture.toPath());
        } catch (Exception e) {
            logger.error("Exception occurred sending readings", e);
        }
    }

}
