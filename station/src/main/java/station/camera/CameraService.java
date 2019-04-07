package station.camera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import station.Station;
import station.sendservice.SendService;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

@ConditionalOnProperty(value = "station.camera.enabled", havingValue = "true")
@Service
public class CameraService {
    private static final Logger logger = LoggerFactory.getLogger(CameraService.class);

    private Station station;

    private SendService sendService;

    @Autowired
    public CameraService(Station station, SendService sendService) {
        this.station = station;
        this.sendService = sendService;
    }

    @Async
    @Scheduled(fixedRateString = "${station.picture-interval-secs:60000}", initialDelay = 0)
    public void collectAndReportPicture() {
        logger.info("snapping a pic...");
        File pictureFile = null;
        try {
            pictureFile = station.takePicture();
            pictureFile.setWritable(true);

            sendService.send(station.getUUID(), ZonedDateTime.now(), pictureFile);

        } catch (IOException e) {
            logger.error("Exception occurred taking/sending picture", e);
        }
    }
}
