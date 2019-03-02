package station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reading.Reading;
import station.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationImpl implements Station {

    private static final Logger logger = LoggerFactory.getLogger(StationImpl.class);

    private List<Sensor> sensors;

    @Autowired
    public StationImpl(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    @Scheduled(fixedRateString ="${station.reading-interval-secs:60000}", initialDelay = 0)
    public List<Reading> readAllSensors() {
        List<Reading> allReadings = new ArrayList<>();

        sensors.forEach(sensor -> allReadings.addAll(sensor.read()));

        return allReadings;
    }

}
