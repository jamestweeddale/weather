package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reading.StationReadings;

@RestController
public class ServerRestController {
    private static final Logger logger = LoggerFactory.getLogger(ServerRestController.class);

    @PostMapping(value = "/post/station-readings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postStationReadings(@RequestBody StationReadings stationReadings) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Received data: " + objectMapper.writeValueAsString(stationReadings));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
