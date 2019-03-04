package station.io;

import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.io.gpio.*;
import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Profile("pi")
@Service
public class GpioServiceImpl implements GpioService {
    private static final Logger logger = LoggerFactory.getLogger(GpioServiceImpl.class);

    private GpioController gpio;

    private AdcGpioProvider analogToDigitalProvider;

    @Autowired
    public GpioServiceImpl() throws IOException {
        gpio = GpioFactory.getInstance();
        gpio.setShutdownOptions(true, PinState.LOW);

        analogToDigitalProvider = new MCP3008GpioProvider(SpiChannel.CS0,
                SpiDevice.DEFAULT_SPI_SPEED,
                SpiDevice.DEFAULT_SPI_MODE,
                false);

        logger.info("Started PI GPIO service...");
    }


    @Override
    public void writePinState(Pin pin, PinState pinState) {
        GpioPinDigitalOutput pinObj = gpio.provisionDigitalOutputPin(pin, PinState.HIGH);
        pinObj.setState(pinState);
    }

    @Override
    public PinState readPinState(Pin pin) {
        return null;
    }

    @Override
    public double readAnalogValue(Pin pin) {

        double returnVal;
        GpioPinAnalogInput gpioPinAnalogInput = null;

        try {
            gpioPinAnalogInput = gpio.provisionAnalogInputPin(analogToDigitalProvider, pin);

            returnVal = gpioPinAnalogInput.getValue();
        } finally {
            try {
                gpio.unprovisionPin(gpioPinAnalogInput);
            } catch (Exception e) {
                logger.error("Exception occurred unprovisioning analog pin", e);
            }
        }

        return returnVal;
    }
}
