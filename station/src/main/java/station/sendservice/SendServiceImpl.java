package station.sendservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reading.StationReadings;
import station.StationApplication;

@Service
public class SendServiceImpl implements SendService {
    private static final Logger logger = LoggerFactory.getLogger(StationApplication.class);

    private RestTemplate restTemplate;

    private String serverHost;

    private Integer serverPort;

    private String serverRestEndpoint;

    private Boolean useHttps;

    @Autowired
    public SendServiceImpl(RestTemplate restTemplate,
                           @Value("${station.serverHost}") String serverHost,
                           @Value("${station.serverPort:@null}") Integer serverPort,
                           @Value("${station.serverRestEndpoint}") String serverRestEndpoint,
                           @Value("${station.useHttps:false}") Boolean useHttps) {
        this.restTemplate = restTemplate;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.serverRestEndpoint = serverRestEndpoint;
        this.useHttps = useHttps;
        this.serverRestEndpoint = constructURL();
    }



    @Override
    public void send(StationReadings readings) throws Exception {

        try {
            restTemplate.postForObject(
                    serverRestEndpoint,
                    readings,
                    StationReadings.class);
        }catch (Exception e){
            logger.error("",e);
        }
    }

    private String constructURL() {

        String http = (useHttps)? "https://" : "http://";

        String port = (serverPort == null) ? "" : (":"+serverPort);

        return http.concat(serverHost).concat(port).concat(serverRestEndpoint);
    }
}
