package min.project.fms.util;

import io.minio.*;
import io.minio.errors.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Component
public class FileUploader {

    static final String BUCKET_NAME = "fms";
    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://127.0.0.1:9000/")
                    .credentials("minioadmin", "minioadmin")
                    .build();

    private void createBucketIfNotExist() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
    public void upload(MultipartFile file) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        createBucketIfNotExist();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(BUCKET_NAME).object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}