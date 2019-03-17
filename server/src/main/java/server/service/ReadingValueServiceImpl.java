package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.ReadingValueEntity;
import server.repository.ReadingValueRepository;


@Service
public class ReadingValueServiceImpl implements ReadingValueService {

    private ReadingValueRepository readingValueRepository;

    @Autowired
    public ReadingValueServiceImpl(ReadingValueRepository readingValueRepository) {
        this.readingValueRepository = readingValueRepository;
    }

    @Override
    public Iterable<ReadingValueEntity> saveAll(Iterable<ReadingValueEntity> readingEntities) {
        return readingValueRepository.saveAll(readingEntities);
    }

}
