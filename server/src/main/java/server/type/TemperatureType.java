package server.type;

import reading.ReadingUnits;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TemperatureType implements ReadingType {

    private static final String TYPE_NAME = "TEMPERATURE";

    private static final ReadingUnits DEFAULT_UNITS = ReadingUnits.FARENHEIT;

    private Set<ReadingUnits> supportedUnits = new HashSet<>(Arrays.asList(ReadingUnits.FARENHEIT, ReadingUnits.CELCIUS));

    @Override
    public boolean supportsUnit(ReadingUnits unit) {
        return supportedUnits.contains(unit);
    }

    @Override
    public Double convertToDefaultUnits(Double value, ReadingUnits currentUnits) {
        switch(currentUnits){
            case FARENHEIT: return value;
            case CELCIUS: return (value * 1.8) + 32;
            default: throw new IllegalArgumentException("Units not supported by type Temperature" + currentUnits.name());
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
