package server.type;

import reading.ReadingUnits;

public interface ReadingType {

    boolean supportsUnit(ReadingUnits unit);

    Double convertToDefaultUnits(Double value, ReadingUnits readingUnits);

    ReadingUnits getDefaultUnits();

    String getTypeName();
}
