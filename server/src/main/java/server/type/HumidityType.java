package server.type;

import reading.ReadingUnits;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HumidityType implements ReadingType {

    private static final String TYPE_NAME = "HUMIDITY";

    private static final ReadingUnits DEFAULT_UNITS = ReadingUnits.RELATIVE_HUMIDITY;

    private Set<ReadingUnits> supportedUnits = new HashSet<>(Arrays.asList(ReadingUnits.RELATIVE_HUMIDITY));

    @Override
    public boolean supportsUnit(ReadingUnits unit) {
        return supportedUnits.contains(unit);
    }

    @Override
    public Double convertToDefaultUnits(Double value, ReadingUnits currentUnits) {
        switch(currentUnits){
            case RELATIVE_HUMIDITY: return value;
            default: throw new IllegalArgumentException("Units not supported by type Humidity" + currentUnits.name());
        }
    }

    @Override
    public ReadingUnits getDefaultUnits() {
        return DEFAULT_UNITS;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }
}
