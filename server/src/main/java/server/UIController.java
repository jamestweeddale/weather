package server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;
import server.entity.StationEntity;
import server.service.ReadingValueService;
import server.service.StationService;

import java.util.List;

@RestController
@CrossOrigin
public class UIController {

    private StationService stationService;

    private ReadingValueService readingValueService;

    public UIController(StationService stationService, ReadingValueService readingValueService) {
        this.stationService = stationService;
        this.readingValueService = readingValueService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/station-list")
    public List<StationEntity> getStationList(){
        return stationService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/station/{stationId}/latest-reading/{readingKeyId}")
    public ReadingValueEntity getLatestReadingFor(@PathVariable(name="stationId") Long stationId, @PathVariable(name="readingKeyId") Long readingKeyId){
        return readingValueService.getLatestFor(stationId, readingKeyId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/station/{stationId}/reading-keys")
    public List<ReadingKeyEntity> getReadingKeysForStation(@PathVariable(name="stationId") Long stationId){
        return readingValueService.getDistinctReadingKeysForStation(stationId);
    }
}
