package server.service;

import server.entity.Station;

import java.util.Optional;


public interface StationService {

    Station save(Station station);

    Iterable<Station> getAll();

    Optional<Station> getById(Long id);
}
