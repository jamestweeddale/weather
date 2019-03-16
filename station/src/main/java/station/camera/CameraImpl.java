package station.camera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Uses raspistill to capture an image from attached camera
 */
@Service
public class CameraImpl implements Camera{
    private static final Logger logger = LoggerFactory.getLogger(CameraImpl.class);

    private static final String COMMAND = "raspistill -o ";
    private static final String FILE_NAME = "newcap.jpg";
    private final String fileDir;

    @Autowired
    public CameraImpl(@Value("${station.camera.fileDir:/tmp/weather-images}") String fileDir) {
        this.fileDir = fileDir;
        createDirIfDNE();
    }

    //todo: run on separate thread
    @Override
    public File takePicture() throws IOException{
        String fullFilePath = fileDir + File.separator + FILE_NAME;

        Runtime.getRuntime().exec(COMMAND + fullFilePath);

        return new File(fullFilePath);
    }

    private void createDirIfDNE(){
        File file = new File(fileDir);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
