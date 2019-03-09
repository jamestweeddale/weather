package server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class FileStorageServiceTest {

    @Mock
    MultipartFile mockMultipartFile;

    @Captor
    ArgumentCaptor<Path> filePathCaptor;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_filename_creation() throws IOException {
        FileStorageService fileStorageService = new FileStorageService("/testpath");

        FileStorageService spyFileStorageService =  Mockito.spy(fileStorageService);

        Mockito.when(mockMultipartFile.getOriginalFilename()).thenReturn("original_Filename.jpg"); //stub out actual all to write file
        Mockito.doReturn(100L).when(spyFileStorageService).writeFile(any(), filePathCaptor.capture());

        UUID uuid = UUID.fromString("8859bb0a-ad70-46e4-8403-080862c21bf2");
        ZonedDateTime instant = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("GMT"));

        spyFileStorageService.storeFile(uuid, instant, mockMultipartFile);

        String fullFilePath = filePathCaptor.getValue().toString();
        assertEquals("\\testpath\\8859bb0a-ad70-46e4-8403-080862c21bf2\\1970-01-01\\8859bb0a-ad70-46e4-8403-080862c21bf2_1970-01-01_000000.jpg", fullFilePath.substring(fullFilePath.indexOf(":")+1));

    }
}
