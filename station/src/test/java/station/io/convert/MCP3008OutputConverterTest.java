package station.io.convert;

import org.junit.Test;
import station.sensor.AdafruitAnemometer;

import static junit.framework.TestCase.assertEquals;


public class MCP3008OutputConverterTest {

    @Test
    public void test_anemometer_value_conversion_min(){
        MCP3008OutputConverter adafruitAnemometerConverter = new MCP3008OutputConverter(AdafruitAnemometer.SENSOR_VOLTAGE_MIN, AdafruitAnemometer.SENSOR_VOLTAGE_MAX, AdafruitAnemometer.MAX_WIND_SPEED_MPS, 0);

        //.4v is min output of adafruit anemometer sensor (0 mps)
        double result = adafruitAnemometerConverter.convert(.4 / MCP3008OutputConverter.VOLTAGE_CONVERSION_CONSTANT);

        assertEquals(0.0, result);

    }

    @Test
    public void test_anemometer_value_conversion_max(){
        MCP3008OutputConverter adafruitAnemometerConverter = new MCP3008OutputConverter(AdafruitAnemometer.SENSOR_VOLTAGE_MIN, AdafruitAnemometer.SENSOR_VOLTAGE_MAX, AdafruitAnemometer.MAX_WIND_SPEED_MPS, 0);

        //2v is max output of adafruit anemometer sensor (32.4 mps)
        double result = adafruitAnemometerConverter.convert(2.0 / MCP3008OutputConverter.VOLTAGE_CONVERSION_CONSTANT);

        assertEquals(AdafruitAnemometer.MAX_WIND_SPEED_MPS, result);
    }

}
