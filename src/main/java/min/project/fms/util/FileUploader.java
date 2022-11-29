package min.project.fms.util;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static min.project.fms.util.Constant.*;

@Component
public class FileUploader {

    private final Log logger = LogFactory.getLog(FileUploader.class);
    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint(MINIO_ADDRESS)
                    .credentials(MINIO_ACCESS_KEY, MINIO_SECRET_KEY)
                    .build();

    private void createBucketIfNotExist() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            }
        } catch (MinioException e) {
            logger.debug("Error occurred: " + e);
            logger.debug("HTTP trace: " + e.httpTrace());
        }
    }
    public void upload(String storagePathName, MultipartFile file) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        createBucketIfNotExist();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(BUCKET_NAME)
                            .object(storagePathName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
        } catch (MinioException e) {
            logger.debug("Error occurred: " + e);
            logger.debug("HTTP trace: " + e.httpTrace());
        }
    }

    public String getUrl(String storagePathName) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        createBucketIfNotExist();

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(BUCKET_NAME)
                            .object(storagePathName)
                            .expiry(1, TimeUnit.HOURS)
                            .build());
        } catch (MinioException e) {
            logger.debug("Error occurred: " + e);
            logger.debug("HTTP trace: " + e.httpTrace());
        }

        return "";
    }
}
