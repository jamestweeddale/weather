package server.type;

import org.springframework.stereotype.Service;
import reading.ReadingUnits;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReadingTypeRegistry {

    private Set<ReadingType> readingTypeSet;


    public ReadingTypeRegistry() {
        readingTypeSet = new HashSet<>();
        this.readingTypeSet.add(new SpeedType());
        this.readingTypeSet.add(new TemperatureType());
    }

    public ReadingType getTypeForUnits(ReadingUnits readingUnits){
        for(ReadingType readingType : readingTypeSet){
            if(readingType.supportsUnit(readingUnits)){
                return readingType;
            }
        }

        throw new IllegalArgumentException("Supporting type not found for units " + readingUnits);
    }

    public ReadingType getTypeByName(String name){
        for(ReadingType readingType : readingTypeSet){
            if(readingType.getTypeName().equalsIgnoreCase(name)){
                return readingType;
            }
        }

        throw new IllegalArgumentException("reading type registry has no supported type for " + name);
    }
}
