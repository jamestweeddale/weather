package station.sensor;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reading.Humidity;
import reading.Reading;
import reading.Temperature;
import reading.units.HumidityUnits;
import reading.units.TemperatureUnits;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemperatureHumiditySensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(TemperatureHumiditySensor.class);

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList();

        try {
            I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
            //SHT020 I2C address is 0x40
            I2CDevice device = bus.getDevice(0x40);

            // Send humidity measurement command, NO HOLD MASTER
            device.write((byte) 0xF5);
            Thread.sleep(500);

            // Read 2 bytes of humidity data
            // humidity msb, humidity lsb
            byte[] data = new byte[2];
            device.read(data, 0, 2);

            // Convert the data
            double humidity = (((((data[0] & 0xFF) * 256.0) + (data[1] & 0xFF)) * 125.0) / 65536.0) - 6;

            // Send temperature measurement command, NO HOLD MASTER
            device.write((byte) 0xF3);
            Thread.sleep(500);

            // Read 2 bytes of temperature data
            // temp msb, temp lsb
            device.read(data, 0, 2);

            // Convert the data
            double cTemp = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.72) / 65536.0) - 46.85;
            double fTemp = cTemp * 1.8 + 32;

            ZonedDateTime readingTime = ZonedDateTime.now();
            readings.add(new Temperature(fTemp, TemperatureUnits.FARENHEIT, readingTime));
            readings.add(new Humidity(humidity, HumidityUnits.RH, readingTime));

        } catch (IOException | I2CFactory.UnsupportedBusNumberException | InterruptedException e) {
           logger.error("Exception occurred reading temperature/humidity",e);
        }

        return readings;
    }

}
