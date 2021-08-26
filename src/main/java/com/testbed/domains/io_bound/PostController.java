package com.testbed.domains.io_bound;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;

  @PostMapping("/post")
  public Post createPost(@RequestBody Post post) {
    return postRepository.save(Post.builder().content(post.getContent()).build());
  }

  @GetMapping("/posts")
  public List<Post> getPostList() {
    return postRepository.findAll();
  }
}
