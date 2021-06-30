package com.testbed.domains.s3.presentation;

import com.testbed.domains.s3.application.S3fileUploadService;
import com.testbed.domains.s3.application.dto.FileUploadRequest;
import com.testbed.domains.s3.application.dto.FileUploadResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3FileUploadController {

  private final S3fileUploadService s3fileUploadService;

  @PostMapping("/upload")
  public ResponseEntity<FileUploadResponse> upload(
      @RequestParam FileUploadRequest fileUploadRequest,
      @RequestParam List<MultipartFile> multipartFileList) {
    return ResponseEntity.ok(s3fileUploadService.upload(fileUploadRequest, multipartFileList));
  }
}
