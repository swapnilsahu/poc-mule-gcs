package com.mule.google.cloud.storage;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Upload {
	public static void uploadObject(String projectId, String bucketName, String objectName, String filePath)
			throws IOException {

		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

		System.out.println(
				// Logger to get success message
				"File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
	}
}