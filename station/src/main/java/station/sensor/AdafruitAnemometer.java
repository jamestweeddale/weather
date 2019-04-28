package station.sensor;

import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reading.Reading;
import reading.ReadingUnits;
import station.io.GpioService;
import station.io.convert.AnalogToDigitalValueConverter;
import station.io.convert.MCP3008OutputConverter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnProperty(value = "station.anemometer.enabled", havingValue = "true")
public class AdafruitAnemometer implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(AdafruitAnemometer.class);

    private static final String WIND_SPEED_READING_NAME = "Wind Speed";

    //values from Adafruit anemometer documentation
    public static final double SENSOR_VOLTAGE_MIN = 0.40; //min sensor output voltage from anemometer spec
    public static final double SENSOR_VOLTAGE_MAX = 2.0; //max sensor output voltage from anemometer spec
    public static final double MAX_WIND_SPEED_MPS = 32.4; //wind speed in meters/sec corresponding to maximum voltage

    private GpioService gpioService;

    private Pin analogPin;

    private AnalogToDigitalValueConverter analogToDigitalValueConverter;

    @Autowired
    public AdafruitAnemometer(GpioService gpioService,
                              @Value("${station.sensor.anemometer.analogChannel:0}") Integer pinIndex,
                              @Value("${station.mcp3008.adjustmentValue:0}") Double adjustmentValue) {
        this.gpioService = gpioService;
        this.analogPin = MCP3008Pin.ALL[pinIndex];
        this.analogToDigitalValueConverter = new MCP3008OutputConverter(SENSOR_VOLTAGE_MIN, SENSOR_VOLTAGE_MAX, MAX_WIND_SPEED_MPS, adjustmentValue);
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList<>();

        try {
            double val = gpioService.readAnalogValue(analogPin);
            double mps = analogToDigitalValueConverter.convert(val);
            readings.add(new Reading(WIND_SPEED_READING_NAME, mps, ReadingUnits.METERS_PER_SECOND, ZonedDateTime.now()));
        }catch (Exception e){
            logger.error("Problem reading anemometer", e);
        }

        return readings;
    }

}

