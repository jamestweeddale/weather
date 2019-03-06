package reading;

import java.util.List;
import java.util.UUID;

public class StationReadings {
    private UUID stationUUID;
    private List<Reading> readings;

    public StationReadings() {
    }

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
