package station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reading.Reading;
import station.camera.Camera;
import station.sensor.Sensor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StationImpl implements Station {

    private static final Logger logger = LoggerFactory.getLogger(StationImpl.class);

    private UUID uuid;

    private List<Sensor> sensors;

    private Camera camera;


    @Autowired
    public StationImpl(@Value("${station.uuid:}") UUID uuid,
                       List<Sensor> sensors,
                       Camera camera) {
        this.uuid = uuid;
        this.sensors = sensors;
        this.camera = camera;
        logger.info("Started station: {}", uuid);
    }


    @Override
    public List<Reading> readAllSensors() {
        List<Reading> allReadings = new ArrayList<>();

        sensors.forEach(sensor -> allReadings.addAll(sensor.read()));

        for(Reading reading : allReadings){
            logger.debug("{} {}", reading.getValue(), reading.getUnits());
        }

        return allReadings;
    }

    @Override
    public File takePicture() throws IOException {
        return camera.takePicture();
    }

    public UUID getUUID() {
        return uuid;
    }
}
