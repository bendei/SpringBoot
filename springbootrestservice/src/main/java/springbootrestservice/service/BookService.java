package springbootrestservice.service;


import java.util.List;

import springbootrestservice.excpetions.ResourceNotFoundException;
import springbootrestservice.model.Book;
import springbootrestservice.repository.BookSimple;
import springbootrestservice.repository.BookTitleView;

public interface BookService {

	public Book getBook(int id);
	
	public List<Book> getAllBooks() throws ResourceNotFoundException;
	
	public Book saveBook(Book book);
	
	public List<BookTitleView> findByTitle(String title);
	
	//public List<BookSimple> findBySellers_name(String name);
}
