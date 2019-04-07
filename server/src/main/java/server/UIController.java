package server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import server.entity.ReadingKeyEntity;
import server.entity.ReadingValueEntity;
import server.entity.StationEntity;
import server.service.ReadingValueService;
import server.service.StationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class UIController {

    private StationService stationService;

    private ReadingValueService readingValueService;

    private FileStorageService fileStorageService;

    public UIController(StationService stationService, ReadingValueService readingValueService,
    FileStorageService fileStorageService) {
        this.stationService = stationService;
        this.readingValueService = readingValueService;
        this.fileStorageService = fileStorageService;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/station/{stationUuid}/latest-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getLatestImageForStation(@PathVariable(name="stationUuid") UUID stationUuid) throws FileNotFoundException, IOException {
        return Files.readAllBytes(fileStorageService.getLatestForStation(stationUuid).toPath());
    }
}
