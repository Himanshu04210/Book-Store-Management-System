package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.CurrentUserSession;
import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;
import com.masai.Repository.BookRepository;
import com.masai.Repository.SessionRepo;

@Service
public class BookServiceImple implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public List<Book> getAllBooks(String key) throws BookException, UserException {
		
		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		List<Book> books = bookRepository.findAllBooks();
		if(books.isEmpty()) throw new BookException("No Book is present");
		return books;
	}
	
	public Book addBook(String key, Book book)throws BookException, UserException {
		
		
		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		if(key.length()<8) throw new UserException("Role should be admin for adding a book");
		
		Book savedBook = bookRepository.save(book);
		return savedBook;
		
	}
	
	
	public Book searchByIsbn(String key, String isbn) throws BookException, UserException{

		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) throw new BookException("No book is present");
		return book;
	}

	@Override
	public List<Book> searchByTitle(String key, String title) throws BookException, UserException {
		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		
		List<Book> books = bookRepository.findByTitle(title);
		if(books.isEmpty()) throw new BookException("No Book is avaiable");
		return books;
	}

	@Override
	public List<Book> searchByAuthor(String key, String author) throws BookException, UserException {
		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		
		List<Book> books = bookRepository.findByAuthor(author);
		if(books.isEmpty()) throw new BookException("No Book is avaiable");
		return books;
	}

	@Override
	public Book updateBook(String key,Integer bookId,  Book book) throws BookException, UserException {

		
		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new UserException("User Not Logged In with this username");
			
		}
		if(key.length()<8) throw new UserException("Role should be admin for updating a book");
		
		Optional<Book> opt = bookRepository.findById(bookId);
		if(opt.isEmpty()) throw new BookException("No Book is avaiable");
		
		Book existingBook = opt.get();
		
		existingBook.setAuthor(book.getAuthor());
		existingBook.setAvailabilityStatus(book.getAvailabilityStatus());
		existingBook.setDescription(book.getDescription());
		existingBook.setPrice(book.getPrice());
		existingBook.setISBN(book.getISBN());
		
		Book savedBook = bookRepository.save(opt.get());
		return savedBook;
	}
	
	
	
	
}	
