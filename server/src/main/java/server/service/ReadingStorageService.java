package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reading.Reading;
import reading.StationReadings;
import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;
import server.entity.StationEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingStorageService {

    private ReadingValueService readingValueService;

    private ReadingKeyService readingKeyService;

    private StationService stationService;

    @Autowired
    public ReadingStorageService(ReadingValueService readingValueService, ReadingKeyService readingKeyService, StationService stationService) {
        this.readingValueService = readingValueService;
        this.readingKeyService = readingKeyService;
        this.stationService = stationService;
    }

    public Iterable<ReadingValueEntity> store(StationReadings stationReadings){
        StationEntity stationEntity = stationService.getByUUID(stationReadings.getStationUUID()).get();

        List<ReadingValueEntity> readingValueEntityList = new ArrayList<>();
        for(Reading reading : stationReadings.getReadings()) {
            ReadingKeyEntity readingKeyEntity = readingKeyService.getForReading(reading);
            readingValueEntityList.add(new ReadingValueEntity(readingKeyEntity, stationEntity, reading.getValue(), reading.getTime()));
        }

        return readingValueService.saveAll(readingValueEntityList);
    }
}
