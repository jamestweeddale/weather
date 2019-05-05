package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path baseFileStorageLocation;

    private Boolean archiveEnabled;

    @Autowired
    public FileStorageService(@Value("${server.picture.uploads.fileStoragePath:/opt/weather/station-images}") String fileStoragePath,
                              @Value("${server.picture.archiveEnabled:false}") Boolean archiveEnabled) throws IOException{
        this.baseFileStorageLocation = Paths.get(fileStoragePath).toAbsolutePath().normalize();
        this.archiveEnabled = archiveEnabled;

        try {
            Files.createDirectories(this.baseFileStorageLocation);
        } catch (Exception ex) {
            throw new IOException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void storeFile(UUID stationUUID, ZonedDateTime captureTime, MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new IOException("Filename contains invalid path sequence " + fileName);
            }

            String dateDir = captureTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String fullPath = baseFileStorageLocation.toString() + File.separator + stationUUID + File.separator + dateDir;

            if (createDirectoriesIfDNE(fullPath)) {
                //update most-recent image for station
                writeFile(file.getInputStream(), this.baseFileStorageLocation.resolve(stationUUID.toString() + File.separator + "latest.jpg"));

                if(archiveEnabled) {
                    //copy file to the archive location (station/date/uid)
                    Path targetLocation = this.baseFileStorageLocation.resolve(stationUUID + File.separator + dateDir + File.separator + createFileName(captureTime, stationUUID, extension));
                    writeFile(file.getInputStream(), targetLocation);
                }
            }

        } catch (IOException ex) {
            throw new IOException("Could not store file " + fileName, ex);
        }
    }

    public long writeFile(InputStream inputStream, Path targetLocation) throws IOException {
        return Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    private String createFileName(ZonedDateTime captureTime, UUID stationUUID, String extension) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");

        return stationUUID.toString() + "_" + formatter.format(captureTime) + extension;
    }

    private boolean createDirectoriesIfDNE(String path) {
        File file = new File(path);
        boolean success = true;
        if (!file.exists()) {
            success = file.mkdirs();
        }

        return success;
    }
}
