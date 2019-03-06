package station.sendservice;

import reading.StationReadings;

public interface SendService {
    void send(StationReadings stationReadings) throws Exception;
}
