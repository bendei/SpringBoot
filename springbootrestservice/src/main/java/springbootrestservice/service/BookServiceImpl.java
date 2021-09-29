package springbootrestservice.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootrestservice.excpetions.BookNotFoundException;
import springbootrestservice.excpetions.ResourceNotFoundException;
import springbootrestservice.model.Book;
import springbootrestservice.repository.BookRepository;
import springbootrestservice.repository.BookSimple;
import springbootrestservice.repository.BookTitleView;

@Service
public class BookServiceImpl implements BookService {

	@SuppressWarnings("unused")
	@Autowired
	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository repo) {
		this.bookRepository = repo;
	}
	
	@Override
	public Book getBook(int id) {
		System.out.println("------------------elküldött id:" + id);		// return this.bookRepository.getById(id);
		// a getById EntityNotFoundException -t dob és azt itt nem tudjuk elkapni mert egy szintel feljebb a repository dobja ezért a GlobalExceptionHandler kezeli le
		
		// ez viszont nem dob semmilyen exc, hanem Optional.empty() true t ad vissza -> mi dobunk custom exceptions
		Optional<Book> book = this.bookRepository.findById(id);
		
		if(book.isEmpty()) throw new BookNotFoundException("nincsen ilyen book");
		return book.get();		
	}

	public List<Book> getAllBooks() throws ResourceNotFoundException {
		List<Book> books = this.bookRepository.findAll();
		//Hibernate.initialize(books.);
		
		if (books.isEmpty()) {
			throw new ResourceNotFoundException("nincsennek könyvek");
		}
		
		
		books.forEach(b ->  {
			System.out.println("---sellers: " + b.getSellers());
		});
		return books;
	}
	
	public List<BookTitleView> findByTitle(String title) {
		return this.bookRepository.findByTitle(title);
	}
	
	public Book saveBook(Book book) {
		return this.bookRepository.save(book);
	}
	
//	public List<BookSimple> findBySellers_name(String name) {
//		return this.bookRepository.findBySellers_name(name);
//	}
	
}
