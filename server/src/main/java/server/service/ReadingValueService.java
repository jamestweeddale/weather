package server.service;

import server.entity.ReadingValueEntity;

public interface ReadingValueService {

    Iterable<ReadingValueEntity> saveAll(Iterable<ReadingValueEntity> readingEntities);

}
