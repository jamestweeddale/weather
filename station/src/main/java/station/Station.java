package station;

import reading.Reading;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface Station {

    UUID getUUID();

    List<Reading> readAllSensors();

    File takePicture() throws IOException;

}
