package com.testbed.domains.s3.application.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class FileUploadRequest {
  String script;
  List<Long> deleteIndexList;
}
