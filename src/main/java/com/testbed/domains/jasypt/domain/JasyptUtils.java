package com.testbed.domains.jasypt.domain;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class JasyptUtils {
  private static final List<Filter> FILTER_LIST =
      List.of(
          Filter.builder().depth(3).key("prefix").build(),
          Filter.builder().depth(3).key("suffix").build(),
          Filter.builder().depth(2).key("algorithm").build());

  private JasyptUtils() {}

  private StandardPBEStringEncryptor getStandardPBEStringEncryptor(String password) {
    StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
    standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
    standardPBEStringEncryptor.setPassword(password);

    return standardPBEStringEncryptor;
  }

  public String encrypt(String password, String text) {
    StandardPBEStringEncryptor standardPBEStringEncryptor = getStandardPBEStringEncryptor(password);

    StringBuffer stringBuffer = new StringBuffer("");
    List<Snippet> snippetList = parseText(text);

    for (Snippet snippet : snippetList) {
      if (writeNotEncryptLine(stringBuffer, snippet)) {
        continue;
      }

      if (FILTER_LIST.stream()
          .anyMatch(
              filter ->
                  filter.getKey().equals(snippet.getKey().stripLeading())
                      && filter.getDepth() == snippet.getDepth())) {
        stringBuffer.append(snippet.getValue()).append("\n");
        continue;
      }
      stringBuffer
          .append("ENC(")
          .append(standardPBEStringEncryptor.encrypt(snippet.getValue().strip()))
          .append(")")
          .append("\n");
    }
    return stringBuffer.toString();
  }

  public String decrypt(String password, String text) {
    StandardPBEStringEncryptor standardPBEStringEncryptor = getStandardPBEStringEncryptor(password);

    StringBuffer stringBuffer = new StringBuffer("");
    List<Snippet> snippetList = parseText(text);
    for (Snippet snippet : snippetList) {
      if (writeNotEncryptLine(stringBuffer, snippet)) {
        continue;
      }

      String value = snippet.getValue().strip();
      if (value.startsWith("ENC(") && value.endsWith(")")) {
        value = value.replace("ENC(", "");
        value = value.substring(0, value.length() - 1);
        stringBuffer.append(standardPBEStringEncryptor.decrypt(value)).append("\n");
        continue;
      }

      stringBuffer.append(value).append("\n");
    }
    return stringBuffer.toString();
  }

  private boolean writeNotEncryptLine(StringBuffer stringBuffer, Snippet snippet) {
    if (snippet.getDepth() == -1) {
      stringBuffer.append((snippet.getKey())).append("\n");
      return true;
    }

    stringBuffer.append(snippet.getKey()).append(": ");
    if (snippet.getValue().length() < 1) {
      stringBuffer.append("\n");
      return true;
    }
    return false;
  }

  private List<Snippet> parseText(String text) {
    List<Snippet> snippetList = new LinkedList<>();
    int depth = 0;

    for (String line : text.split("\n")) {
      if (!line.startsWith(" ")) {
        depth = 0;
        line = line.stripTrailing();
      }

      if (!line.contains(":")) {
        snippetList.add(Snippet.builder().depth(-1).key(line).value("").build());
        continue;
      }

      String[] keyValueArray = line.split(":");
      if (keyValueArray.length > 2) {
        for (int i = 2; i < keyValueArray.length; i++) {
          keyValueArray[1] += ":" + keyValueArray[i];
        }
      }

      if (keyValueArray.length == 1) {
        snippetList.add(Snippet.builder().depth(depth).key(keyValueArray[0]).value("").build());
        depth++;
        continue;
      }

      snippetList.add(
          Snippet.builder().depth(depth).key(keyValueArray[0]).value(keyValueArray[1]).build());
    }

    return snippetList;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  private static class Snippet {
    private int depth;
    private String key;
    private String value;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  private static class Filter {
    private int depth;
    private String key;
  }
}
