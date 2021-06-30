package com.testbed.commons.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbed.domains.s3.application.dto.FileUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToFileUploadRequestConverter implements Converter<String, FileUploadRequest> {
  private final ObjectMapper objectMapper;

  @Override
  public FileUploadRequest convert(String source) {
    try {
      return objectMapper.readValue(source, FileUploadRequest.class);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid File Upload");
    }
  }
}
