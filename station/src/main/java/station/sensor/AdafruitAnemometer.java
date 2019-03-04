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
import station.convert.AnalogToDigitalValueConverter;
import station.convert.MCP3008OutputConverter;
import station.sensor.io.GpioService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdafruitAnemometer implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(AdafruitAnemometer.class);

    private GpioService gpioService;

    private Pin analogPin;

    private AnalogToDigitalValueConverter analogToDigitalValueConverter;

    public final static double sensorVoltageMin = 0.40; //min output voltage from anemometer spec
    public final static double sensorVoltageMax = 2.0; //max output voltage from anemometer spec
    public final static double maxWindSpeedMps = 32.4; //wind speed in meters/sec corresponding to maximum voltage

    @Autowired
    public AdafruitAnemometer(GpioService gpioService,
                              @Value("${station.sensor.anemometer.analogChannel:0}") Integer pinIndex) {
        this.gpioService = gpioService;
        this.analogPin = MCP3008Pin.ALL[pinIndex];
        this.analogToDigitalValueConverter = new MCP3008OutputConverter(sensorVoltageMin, sensorVoltageMax, maxWindSpeedMps);
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList();

        double val = gpioService.readAnalogValue(analogPin);
        double mph = analogToDigitalValueConverter.convert(val);
        readings.add(new WindSpeed(mph, SpeedUnits.MPS));

        return readings;
    }

}
