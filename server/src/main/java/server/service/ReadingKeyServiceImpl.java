package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reading.Reading;
import server.entity.ReadingKeyEntity;
import server.repository.ReadingKeyRepository;
import server.type.ReadingType;
import server.type.ReadingTypeRegistry;

import java.util.Optional;

@Service
public class ReadingKeyServiceImpl implements ReadingKeyService {

    private ReadingKeyRepository readingKeyRepository;

    private ReadingTypeRegistry readingTypeRegistry;

    @Autowired
    public ReadingKeyServiceImpl(ReadingKeyRepository readingKeyRepository, ReadingTypeRegistry readingTypeRegistry) {
        this.readingKeyRepository = readingKeyRepository;
        this.readingTypeRegistry = readingTypeRegistry;
    }


    @Override
    public ReadingKeyEntity getForReading(Reading reading) {

        Optional<ReadingKeyEntity> readingKeyEntityOpt = readingKeyRepository.findOneByName(reading.getName());
        if(readingKeyEntityOpt.isPresent() ){
            return readingKeyEntityOpt.get();
        }else{
            ReadingType readingType = readingTypeRegistry.getTypeForUnits(reading.getUnits());
            return readingKeyRepository.save(new ReadingKeyEntity(reading.getName(), readingType.getTypeName()));
        }
    }
}
