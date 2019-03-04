package station.sendservice;

import java.io.IOException;

public interface SendService {
    void send(StationReadings stationReadings) throws IOException;
}
