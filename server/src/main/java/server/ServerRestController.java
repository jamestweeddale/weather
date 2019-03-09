package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reading.StationReadings;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class ServerRestController {
    private static final Logger logger = LoggerFactory.getLogger(ServerRestController.class);

    FileStorageService fileStorageService;

    @Autowired
    public ServerRestController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping(value = "/post/station-readings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postStationReadings(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) StationReadings stationReadings) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            logger.debug("Received data: {}", objectMapper.writeValueAsString(stationReadings));
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

}
