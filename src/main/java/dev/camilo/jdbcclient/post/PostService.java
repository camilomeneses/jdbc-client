package dev.camilo.jdbcclient.post;

import java.util.List;
import java.util.Optional;

public interface PostService {

  List<Post> findAll();

  Optional<Post> findById(String id);

  void create(Post post);

  void update(Post posts, String id);

  void delete(String id);
}
