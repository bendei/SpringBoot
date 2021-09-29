package springbootrestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import springbootrestservice.model.Book;

// http://localhost:8080/h2-console
public interface BookRepository  extends JpaRepository<Book, Integer>{

	@EntityGraph(value = "Book.sellers")
	List<Book> findAll();
	
	List<BookTitleView> findByTitle(String title);
	
//	@EntityGraph(value = "Book.sellers")
//	List<BookSimple> findBySellers_name(String name);
}
