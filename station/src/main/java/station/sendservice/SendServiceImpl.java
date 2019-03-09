package station.sendservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reading.StationReadings;
import station.StationApplication;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class SendServiceImpl implements SendService {
    private static final Logger logger = LoggerFactory.getLogger(StationApplication.class);

    private RestTemplate restTemplate;

    private String serverHost;

    private Integer serverPort;

    private String serverReadingsEndpoint;

    private String serverImagesEndpoint;

    private Boolean useHttps;

    @Autowired
    public SendServiceImpl(RestTemplate restTemplate,
                           @Value("${station.serverHost}") String serverHost,
                           @Value("${station.serverPort:@null}") Integer serverPort,
                           @Value("${station.serverRestEndpoint:/post/station-readings}") String serverReadingsEndpoint,
                           @Value("${station.serverImagesEndpoint:/post/station-images}") String serverImagesEndpoint,
                           @Value("${station.useHttps:false}") Boolean useHttps) {
        this.restTemplate = restTemplate;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.serverReadingsEndpoint = serverReadingsEndpoint;
        this.serverImagesEndpoint = serverImagesEndpoint;
        this.useHttps = useHttps;
    }


    @Override
    public void send(StationReadings readings) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.debug("sending readings : {}", objectMapper.writeValueAsString(readings));

            restTemplate.postForLocation(
                    constructURL(serverReadingsEndpoint),
                    readings);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    public void send(UUID stationUuid, ZonedDateTime captureTime, File imageFile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(imageFile));
        body.add("stationUuid", stationUuid.toString());
        body.add("captureTime", captureTime.format(DateTimeFormatter.ISO_DATE_TIME));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        restTemplate.postForLocation(constructURL(serverImagesEndpoint), requestEntity);
    }


    private String constructURL(String endPoint) {

        String http = (useHttps) ? "https://" : "http://";

        String port = (serverPort == null) ? "" : (":" + serverPort);

        return http.concat(serverHost).concat(port).concat(endPoint);
    }
}
