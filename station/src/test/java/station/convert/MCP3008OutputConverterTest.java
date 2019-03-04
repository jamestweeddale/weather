package station.convert;

import org.junit.Test;
import station.sensor.AdafruitAnemometer;

import static junit.framework.TestCase.assertEquals;


public class MCP3008OutputConverterTest {

    @Test
    public void test_anemometer_value_conversion_min(){
        MCP3008OutputConverter adafruitAnemometerConverter = new MCP3008OutputConverter(AdafruitAnemometer.sensorVoltageMin, AdafruitAnemometer.sensorVoltageMax, AdafruitAnemometer.maxWindSpeedMps);

        //.4v is min output of adafruit anemometer sensor (0 mps)
        double result = adafruitAnemometerConverter.convert(.4 / MCP3008OutputConverter.voltageConversionConstant);

        assertEquals(0.0, result);

    }

    @Test
    public void test_anemometer_value_conversion_max(){
        MCP3008OutputConverter adafruitAnemometerConverter = new MCP3008OutputConverter(AdafruitAnemometer.sensorVoltageMin, AdafruitAnemometer.sensorVoltageMax, AdafruitAnemometer.maxWindSpeedMps);

        //2v is max output of adafruit anemometer sensor (32.4 mps)
        double result = adafruitAnemometerConverter.convert(2.0 / MCP3008OutputConverter.voltageConversionConstant);

        assertEquals(AdafruitAnemometer.maxWindSpeedMps, result);
    }

}
