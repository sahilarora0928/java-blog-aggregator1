package cz.jiripinkas.jba.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	// spring data JPA will generate implementation of this method which will be select all blogs which are associated with some user entity.
		List<Blog> findByUser(User user);
}
