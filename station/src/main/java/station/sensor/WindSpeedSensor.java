package station.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reading.Reading;
import station.sensor.io.GpioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class WindSpeedSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(WindSpeedSensor.class);

    private GpioService gpioService;

    @Autowired
    public WindSpeedSensor(GpioService gpioService) {
        this.gpioService = gpioService;
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList();
        logger.debug("reading wind speed");
        return readings;
    }
}
