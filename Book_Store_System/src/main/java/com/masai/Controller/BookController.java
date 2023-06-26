package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;
import com.masai.Service.BookService;

public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books/{key}")
	public ResponseEntity<List<Book>> getAllBooks(@PathVariable String key) throws BookException, UserException {
		List<Book> books = bookService.getAllBooks(key);
		return new ResponseEntity<List<Book>>(books, HttpStatus.FOUND);
	}
	
	@PostMapping("/book/{key}")
	public ResponseEntity<Book> addBook(@PathVariable String key, @RequestBody Book book) throws BookException, UserException {
		Book savedBook = bookService.addBook(key, book);
		return new ResponseEntity<>(savedBook, HttpStatus.FOUND);
	}
	
	@GetMapping("/book/{key}")
	public ResponseEntity<Book> searchByIsbnBook(@PathVariable String key, @RequestParam(required = false) String isbn ) throws BookException, UserException {
		Book Book = bookService.searchByIsbn(key, isbn);
		return new ResponseEntity<>(Book, HttpStatus.FOUND);
	}
	
	

	@GetMapping("/book/{key}")
	public ResponseEntity<List<Book>> searchByTitleBook(@PathVariable String key, @RequestParam(required = false) String title ) throws BookException, UserException {
		List<Book> Books = bookService.searchByTitle(key, title);
		return new ResponseEntity<>(Books, HttpStatus.FOUND);
	}
	

	@GetMapping("/book/{key}")
	public ResponseEntity<List<Book>> searchByAuthorBook(@PathVariable String key, @RequestParam(required = false) String author ) throws BookException, UserException {
		List<Book> Books = bookService.searchByAuthor(key, author);
		return new ResponseEntity<>(Books, HttpStatus.FOUND);
	}
	
	@PatchMapping("/book/{key}/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable String key, @PathVariable Integer bookId , @RequestBody Book book) throws BookException, UserException {
		Book savedBook = bookService.updateBook(key, bookId, book);
		return new ResponseEntity<>(savedBook, HttpStatus.FOUND);
	}
	
}
/*public List<Book> getAllBooks(String key) throws BookException, UserException;
	public Book addBook(String key, Book book)throws BookException, UserException;
	public Book searchByIsbn(String key, String isbn) throws BookException, UserException;
	public List<Book> searchByTitle(String key, String title) throws BookException, UserException;
	public List<Book> searchByAuthor(String key, String author) throws BookException, UserException;
	public Book updateBook(String key,Integer bookId, Book book) throws BookException, UserException;
}
 * 
 */
