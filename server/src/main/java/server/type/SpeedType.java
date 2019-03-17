package server.type;

import reading.ReadingUnits;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpeedType implements ReadingType {
    private static final String TYPE_NAME = "SPEED";

    private static final ReadingUnits DEFAULT_UNITS = ReadingUnits.METERS_PER_SECOND;

    private Set<ReadingUnits> supportedUnits = new HashSet<>(Arrays.asList(ReadingUnits.FEET_PER_SECOND, ReadingUnits.KILOMETERS_PER_HOUR, ReadingUnits.MILES_PER_HOUR, ReadingUnits.METERS_PER_SECOND));

    @Override
    public boolean supportsUnit(ReadingUnits unit) {

        return supportedUnits.contains(unit);
    }

    @Override
    public Double convertToDefaultUnits(Double value, ReadingUnits currentUnits) {
        switch(currentUnits){
            case METERS_PER_SECOND: return value;
            case FEET_PER_SECOND: return (value * 0.3048);
            case KILOMETERS_PER_HOUR: return (value * 0.277778);
            case MILES_PER_HOUR: return (value * 0.44704);
            default: throw new IllegalArgumentException("Units not supported by type Speed" + currentUnits.name());
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
