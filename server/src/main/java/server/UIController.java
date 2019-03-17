package server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.StationEntity;
import server.service.StationService;

@RestController
public class UIController {

    private StationService stationService;

    public UIController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/station-list")
    public Iterable<StationEntity> getStationList(){
        return stationService.getAll();
    }

}
