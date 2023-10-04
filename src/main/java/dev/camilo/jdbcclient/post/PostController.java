package dev.camilo.jdbcclient.post;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;


  public PostController(@Qualifier("clientPostService") PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  Optional<Post> findById(@PathVariable String id) {
    return postService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  void create(@RequestBody Post post) {
    postService.create(post);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void update(@PathVariable String id, @RequestBody Post post) {
    postService.update(post, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable String id) {
    postService.delete(id);
  }
}
