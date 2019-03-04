package station;

import reading.Reading;

import java.util.List;
import java.util.UUID;

public interface Station {

    UUID getUUID();

    List<Reading> readAllSensors();

}
