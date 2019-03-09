package station.sendservice;

import reading.StationReadings;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface SendService {
    void send(StationReadings stationReadings) throws Exception;

    void send(UUID stationUuid, ZonedDateTime captureTime, File imageFile);
}
