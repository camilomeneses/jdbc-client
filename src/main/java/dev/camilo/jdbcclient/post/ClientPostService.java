package dev.camilo.jdbcclient.post;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClientPostService implements PostService {

  private final JdbcClient jdbcClient;

  public ClientPostService(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }


  @Override
  public List<Post> findAll() {
    return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post")
        .query(Post.class)
        .list();
  }

  @Override
  public Optional<Post> findById(String id) {
    return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post WHERE id = :id")
        .param("id", id)
        .query(Post.class)
        .optional();
  }

  @Override
  public void create(Post post) {
    int update = jdbcClient.sql("INSERT INTO post(id,title,slug,date,time_to_read,tags) values(?,?,?,?,?,?)")
        .params(List.of(post.id(), post.title(), post.slug(), post.date(), post.timeToRead(), post.tags()))
        .update();

    Assert.state(update == 1, "Failed to create post " + post.title());
  }

  @Override
  public void update(Post posts, String id) {
    int update = jdbcClient.sql("UPDATE post SET title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? WHERE id = ?")
        .params(List.of(posts.title(), posts.slug(), posts.date(), posts.timeToRead(), posts.tags(), id))
        .update();

    Assert.state(update == 1, "Failed to update post " + posts.title());
  }

  @Override
  public void delete(String id) {
    int update = jdbcClient.sql("DELETE FROM post WHERE id = :id")
        .param("id", id)
        .update();

    Assert.state(update == 1, "Failed to delete post " + id);
  }
}
