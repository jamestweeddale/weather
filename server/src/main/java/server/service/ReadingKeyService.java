package server.service;

import reading.Reading;
import server.entity.ReadingKeyEntity;

import java.util.List;

public interface ReadingKeyService {

    ReadingKeyEntity getForReading(Reading reading);

    List<ReadingKeyEntity> getAll();

}
