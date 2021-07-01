package com.testbed.domains.s3.application;

import com.testbed.domains.s3.application.dto.FileUploadRequest;
import com.testbed.domains.s3.application.dto.FileUploadResponse;
import com.testbed.domains.s3.domain.S3FileUploader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3fileUploadService {

  private final S3FileUploader s3FileUploader;

  public FileUploadResponse upload(
      FileUploadRequest fileUploadRequest, List<MultipartFile> multipartFileList) {
    if (fileUploadRequest.getDeleteIndexList().size() > 0) {
      s3FileUploader.delete(fileUploadRequest.getUserIndex(), fileUploadRequest.getDeleteIndexList());
    }

    List<String> pathList = s3FileUploader.upload(fileUploadRequest.getUserIndex(), multipartFileList);
    return FileUploadResponse.builder().accessUrlList(pathList).build();
  }
}
