package com.testbed.domains.s3.application;

import com.testbed.domains.s3.application.dto.FileUploadRequest;
import com.testbed.domains.s3.application.dto.FileUploadResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3fileUploadService {

  public FileUploadResponse upload(
      FileUploadRequest fileUploadRequest, List<MultipartFile> multipartFileList) {
    System.out.println(fileUploadRequest.getScript());
    System.out.println(multipartFileList.get(0).getOriginalFilename());

    return FileUploadResponse.builder().accessUrl("Not Implemented").build();
  }
}
