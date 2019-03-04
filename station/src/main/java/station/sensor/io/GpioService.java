package station.sensor.io;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public interface GpioService {

    void writePinState(Pin pin, PinState pinState);
    PinState readPinState(Pin pin);
    double readAnalogValue(Pin pin);

}
