package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;
import server.repository.ReadingValueRepository;

import java.util.List;


@Service
public class ReadingValueServiceImpl implements ReadingValueService {

    private ReadingValueRepository readingValueRepository;

    @Autowired
    public ReadingValueServiceImpl(ReadingValueRepository readingValueRepository) {
        this.readingValueRepository = readingValueRepository;
    }

    @Override
    public List<ReadingValueEntity> saveAll(Iterable<ReadingValueEntity> readingEntities) {
        return readingValueRepository.saveAll(readingEntities);
    }

    @Override
    public List<ReadingKeyEntity> getDistinctReadingKeysForStation(Long stationId) {
        return readingValueRepository.findReadingKeysForStation(stationId);
    }

    @Override
    public ReadingValueEntity getLatestFor(Long stationId, Long readingKeyId) {
        return readingValueRepository.findFirstByStationIdAndReadingKeyIdOrderByIdDesc(stationId, readingKeyId);
    }
}
