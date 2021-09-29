package springbootrestservice;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootrestservice.excpetions.ResourceNotFoundException;
import springbootrestservice.model.Book;
import springbootrestservice.repository.BookRepository;
import springbootrestservice.repository.BookSimple;
import springbootrestservice.repository.BookTitleView;
import springbootrestservice.service.BookService;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class BookController {
	
	@SuppressWarnings("unused")
	@Autowired
	private BookService bookService;
	
	private BookRepository bookRepository;

	private static final String success_status = "success";
	private static final String error_status = "error";
	private static final int code_success = 100;
	
	public BookController(BookService service, BookRepository bookRepository) {
		this.bookService = service;
		this.bookRepository = bookRepository;
	}
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping(value="/book")
	public ResponseEntity<Book> book(@RequestBody @Valid Book request)  {
		Book book = this.bookService.saveBook(request);
		return new ResponseEntity<Book>(book, getNoCacheHeaders(), HttpStatus.OK);			
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {		
		Book book = this.bookService.getBook(id);	
		
		//book.getBookSellers().stream().forEach(bs -> System.out.println(bs.getName()));
		
		return new ResponseEntity<Book>(book, getNoCacheHeaders(), HttpStatus.OK);	
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "book")
	public ResponseEntity<List<Book>> getAllBooks() {
		
		// just for practice
		
//		List<BookTitleView> bookok = this.bookRepository.findByTitle("REST Doksi");
//		System.out.println(bookok.size());
//		for (BookTitleView title : bookok) {
//			System.out.println("title:" + title.getTitle());
//		}
		
//		List<BookSimple> bySellerNameBooks = this.bookService.findBySellers_name("Bende seller");
//		System.out.println("bySellerNameBooks:" + bySellerNameBooks.size());
//		for (BookSimple simple : bySellerNameBooks) {
//			System.out.println(simple.getIsbn());
//		}
		
		List<Book> books = this.bookService.getAllBooks();		
		return new ResponseEntity<List<Book>>(books, getNoCacheHeaders(), HttpStatus.OK);	
	}
	
	@CrossOrigin("http://localhost:4200")
	@PutMapping(value = "/book/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable Integer id) {
		// ha a request json nem tartalmaz pl title propertit akkor a DB-ben null -al kell felülírni a mezőt !
		
		return this.bookRepository.findById(id).map(
				bo ->  {
					bo.setPageSize(book.getPageSize() != null ? book.getPageSize() : 10);
					bo.setTitle(book.getTitle());
					bo.setIsbn(book.getIsbn());
					bo.setRating(book.getRating());
					return this.bookRepository.save(bo);
				}
				).orElseGet( () ->  {
					book.setId(1);
					book.setPageSize(book.getPageSize() != null ? book.getPageSize() : 10);
					return this.bookRepository.save(book);
				}
			);	          
	}
	
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl("no-cache, must-revalidate");
		headers.setPragma("no-cache");
		LocalDateTime timePoint = LocalDateTime.now();
		headers.setExpires(java.sql.Timestamp.valueOf(timePoint).getTime());
		return headers;
	}       
	
}
