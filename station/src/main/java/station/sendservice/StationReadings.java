package station.sendservice;

import reading.Reading;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"squid:S1948"})
public class StationReadings implements Serializable {
    private UUID stationUUID;
    private List<Reading> readings;

    public StationReadings(UUID stationUUID, List<Reading> readings) {
        this.stationUUID = stationUUID;
        this.readings = readings;
    }

    public UUID getStationUUID() {
        return stationUUID;
    }

    public List<Reading> getReadings() {
        return readings;
    }

}
