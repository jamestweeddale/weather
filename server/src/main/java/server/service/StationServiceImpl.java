package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.Station;
import server.repository.StationRepository;

import java.util.Optional;

@Service
public class StationServiceImpl implements StationService{

    private StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Iterable<Station> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> getById(Long id) {
        return stationRepository.findById(id);
    }
}
