package com.testbed.domains.io_bound;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCacheService {

  private final PostRepository postRepository;

  @Getter
  private Page<Post> firstPostPage;

  @Scheduled(cron = "* * * * * *")
  public void updateFirstPostPage() {
    firstPostPage = postRepository.findAll(PageRequest.of(0, 20, Sort.by("id").descending()));
  }
}
