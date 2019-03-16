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

    private I2CDevice i2CDevice;

    private static final byte DEVICE_I2C_ADDR = 0x40;  //SHT020 I2C address is 0x40

    public TemperatureHumiditySensor() throws IOException, I2CFactory.UnsupportedBusNumberException {
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        i2CDevice = bus.getDevice(DEVICE_I2C_ADDR);
    }

    @Override
    public List<Reading> read() {
        List<Reading> readings = new ArrayList();

        ZonedDateTime readingTime = ZonedDateTime.now();

        try{
            double humidity = readHumidity();
            readings.add(new Humidity(humidity, HumidityUnits.RH, readingTime));
        }catch (IOException e){
            logger.error("Problem reading humidity", e);
        }

        try{
            double temperature = readTemperatureF();
            readings.add(new Temperature(temperature, TemperatureUnits.FARENHEIT, readingTime));
        }catch (IOException e){
            logger.error("Problem reading temperature", e);
        }

        return readings;
    }


    private double readTemperatureF() throws IOException{
        byte[] data = new byte[2];

        // Send temperature measurement command, NO HOLD MASTER
        i2CDevice.write((byte) 0xF3);
        sleep();

        // Read 2 bytes of temperature data
        // temp msb, temp lsb
        i2CDevice.read(data, 0, 2);

        // Convert the data
        double cTemp = (((((data[0] & 0xFF) * 256) + (data[1] & 0xFF)) * 175.72) / 65536.0) - 46.85;
        return cTemp * 1.8 + 32;  //convert to F
    }

    private double readHumidity() throws IOException{
        // Send humidity measurement command, NO HOLD MASTER
        i2CDevice.write((byte) 0xF5);
        sleep();
        // Read 2 bytes of humidity data
        // humidity msb, humidity lsb
        byte[] data = new byte[2];
        i2CDevice.read(data, 0, 2);

        // Convert the data
        return (((((data[0] & 0xFF) * 256.0) + (data[1] & 0xFF)) * 125.0) / 65536.0) - 6;
    }

    private void sleep(){
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            logger.error("Exception while sleeping", e);
        }
    }
}
