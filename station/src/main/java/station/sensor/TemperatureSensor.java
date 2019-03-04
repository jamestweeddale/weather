package station.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reading.Reading;
import station.io.GpioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(TemperatureSensor.class);

    private GpioService gpioService;

    @Autowired
    public TemperatureSensor(GpioService gpioService) {
        this.gpioService = gpioService;
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList();
        logger.debug("reading temperature");
        return readings;
    }
}
