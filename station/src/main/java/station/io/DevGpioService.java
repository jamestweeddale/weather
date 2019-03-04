package station.io;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class DevGpioService implements GpioService {
    private static final Logger logger = LoggerFactory.getLogger(DevGpioService.class);

    @Override
    public void writePinState(Pin pin, PinState pinState) {
        logger.debug("called writePin pin={} state={}", pin, pinState);
    }

    @Override
    public PinState readPinState(Pin pin) {
        logger.debug("called readPinPin pin={}", pin);
        return null;
    }

    @Override
    public double readAnalogValue(Pin pin) {
        logger.debug("called readAnalogValue pin={}", pin);
        return 0.0;
    }
}
