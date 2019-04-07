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
import java.nio.file.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path baseFileStorageLocation;

    @Autowired
    public FileStorageService(@Value("${server.picture.uploads.fileStoragePath:/tmp/station-images}") String fileStoragePath) throws IOException{
        this.baseFileStorageLocation = Paths.get(fileStoragePath).toAbsolutePath().normalize();

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
                // Copy file to the target location (Replacing existing file with the same name)
                Path targetLocation = this.baseFileStorageLocation.resolve(stationUUID + File.separator + dateDir + File.separator + createFileName(captureTime, stationUUID, extension));
                writeFile(file.getInputStream(), targetLocation);
            }

        } catch (IOException ex) {
            throw new IOException("Could not store file " + fileName, ex);
        }
    }

    public long writeFile(InputStream inputStream, Path targetLocation) throws IOException {
        return Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    public File getLatestForStation(UUID stationUuid) throws FileNotFoundException{
        String todaysDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Path folder = baseFileStorageLocation.resolve(stationUuid + File.separator + todaysDate);
        Optional<File> mostRecentFile =
                Arrays.stream(folder.toFile().listFiles())
                        .filter(f -> f.isFile())
                        .max((f1, f2) -> Long.compare(f1.lastModified(),
                                f2.lastModified()));

        return mostRecentFile.orElseThrow(() -> new FileNotFoundException());
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
