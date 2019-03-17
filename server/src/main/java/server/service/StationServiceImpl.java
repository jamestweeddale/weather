package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.StationEntity;
import server.repository.StationRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class StationServiceImpl implements StationService{

    private StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public StationEntity save(StationEntity station) {
        return stationRepository.save(station);
    }

    @Override
    public Iterable<StationEntity> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<StationEntity> getById(Long id) {
        return stationRepository.findById(id);
    }

    @Override
    public Optional<StationEntity> getByUUID(UUID uuid) {
        return stationRepository.findByUuid(uuid);
    }
}
