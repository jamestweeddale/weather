package server.service;

import reading.Reading;
import server.entity.ReadingKeyEntity;

public interface ReadingKeyService {

    ReadingKeyEntity getForReading(Reading reading);

}
