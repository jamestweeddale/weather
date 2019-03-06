package station.sensor;

import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reading.Reading;
import reading.WindSpeed;
import reading.units.SpeedUnits;
import station.io.convert.AnalogToDigitalValueConverter;
import station.io.convert.MCP3008OutputConverter;
import station.io.GpioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdafruitAnemometer implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(AdafruitAnemometer.class);

    //values from Adafruit anemometer documentation
    public static final double SENSOR_VOLTAGE_MIN = 0.40; //min sensor output voltage from anemometer spec
    public static final double SENSOR_VOLTAGE_MAX = 2.0; //max sensor output voltage from anemometer spec
    public static final double MAX_WIND_SPEED_MPS = 32.4; //wind speed in meters/sec corresponding to maximum voltage

    private GpioService gpioService;

    private Pin analogPin;

    private AnalogToDigitalValueConverter analogToDigitalValueConverter;

    @Autowired
    public AdafruitAnemometer(GpioService gpioService,
                              @Value("${station.sensor.anemometer.analogChannel:0}") Integer pinIndex) {
        this.gpioService = gpioService;
        this.analogPin = MCP3008Pin.ALL[pinIndex];
        this.analogToDigitalValueConverter = new MCP3008OutputConverter(SENSOR_VOLTAGE_MIN, SENSOR_VOLTAGE_MAX, MAX_WIND_SPEED_MPS);
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList<>();

        double val = gpioService.readAnalogValue(analogPin);
        double mps = analogToDigitalValueConverter.convert(val);
        readings.add(new WindSpeed(mps, SpeedUnits.MPS));

        return readings;
    }

}

