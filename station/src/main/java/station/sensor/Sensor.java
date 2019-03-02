package station.sensor;

import reading.Reading;

import java.util.List;

public interface Sensor {

    List<Reading> read();

}
