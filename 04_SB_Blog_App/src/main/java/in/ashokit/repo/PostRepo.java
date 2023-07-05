package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Post;

public interface PostRepo extends JpaRepository<Post,Integer> {

}
