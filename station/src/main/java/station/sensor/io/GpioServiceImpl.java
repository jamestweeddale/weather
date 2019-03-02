package station.sensor.io;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("pi")
@Service
public class GpioServiceImpl implements GpioService {
    private static final Logger logger = LoggerFactory.getLogger(GpioServiceImpl.class);

    private GpioController gpio;

    @Autowired
    public GpioServiceImpl(){
        gpio = GpioFactory.getInstance();
        gpio.setShutdownOptions(true, PinState.LOW);
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
    public Double readAnalogValue(Pin pin) {
        return null;
    }
}
