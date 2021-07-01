package com.testbed.domains.s3.application.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class FileUploadRequest {
  private Long userIndex;
  private List<Long> deleteIndexList;
}
