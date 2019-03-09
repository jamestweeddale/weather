package station.camera;

import java.io.File;
import java.io.IOException;

public interface Camera {

    File takePicture() throws IOException;

}
