package com.testbed.domains.io_bound;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private static Integer PAGE_SIZE = 20;
  private final PostRepository postRepository;

  @PostMapping("/post")
  public Post createPost(@RequestBody Post post) {
    return postRepository.save(Post.builder().content(post.getContent()).build());
  }

  @GetMapping("/posts")
  public Page<Post> getPostList(@RequestParam(defaultValue = "1") Integer page) {
    return postRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").descending()));
  }

  @GetMapping("/post/{id}")
  public Post getPostById(@PathVariable Long id) {
    return postRepository.findById(id).get();
  }
}
