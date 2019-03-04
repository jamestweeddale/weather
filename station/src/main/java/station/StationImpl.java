package station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reading.Reading;
import station.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StationImpl implements Station {

    private static final Logger logger = LoggerFactory.getLogger(StationImpl.class);

    private UUID uuid;

    private List<Sensor> sensors;


    @Autowired
    public StationImpl(@Value("${station.uuid:}") UUID uuid,
                       List<Sensor> sensors) {
        this.uuid = uuid;
        this.sensors = sensors;
        logger.info("Started station: " + uuid);
    }


    @Override
    public List<Reading> readAllSensors() {
        List<Reading> allReadings = new ArrayList<>();

        sensors.forEach(sensor -> allReadings.addAll(sensor.read()));

        for(Reading reading : allReadings){
            logger.debug(reading.getValue() + " " + reading.getUnits());
        }

        return allReadings;
    }

    public UUID getUUID() {
        return uuid;
    }
}
