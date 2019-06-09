package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reading.Reading;
import reading.StationReadings;
import server.entity.ReadingKeyEntity;
import server.service.ReadingKeyService;
import server.service.ReadingStorageService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class StationRestController {
    private static final Logger logger = LoggerFactory.getLogger(StationRestController.class);

    private FileStorageService fileStorageService;

    private ReadingStorageService readingStorageService;

    private ReadingKeyService readingKeyService;

    @Autowired
    public StationRestController(FileStorageService fileStorageService, ReadingStorageService readingStorageService, ReadingKeyService readingKeyService) {
        this.fileStorageService = fileStorageService;
        this.readingStorageService = readingStorageService;
        this.readingKeyService = readingKeyService;
    }

    @PostMapping(value = "/post/station-readings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postStationReadings(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) StationReadings stationReadings) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            logger.debug("Received data: {}", objectMapper.writeValueAsString(stationReadings));

            readingStorageService.store(stationReadings);

        } catch (Exception e) {
            logger.error("Error occurred in /post/station-readings", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).build();
    }


    @PostMapping("/post/station-images")
    public ResponseEntity uploadFile(@RequestParam("stationUuid") UUID stationUuid,
                                     @RequestParam("captureTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime captureTime,
                                     @RequestParam("file") MultipartFile file) {
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            logger.debug("Received picture upload...");
            fileStorageService.storeFile(stationUuid, captureTime, file);
        }catch (Exception e){
            logger.error("Failed uploading image", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).build();
    }

    @GetMapping("/reading-keys")
    public List<ReadingKeyEntity> getallReadingKeys(){
            return readingKeyService.getAll();
    }

}
