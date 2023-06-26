package com.masai.Service;

import java.util.List;

import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;

public interface BookService {
	public List<Book> getAllBooks(String key) throws BookException, UserException;
	public Book addBook(String key, Book book)throws BookException, UserException;
	public Book searchByIsbn(String key, String isbn) throws BookException, UserException;
	public List<Book> searchByTitle(String key, String title) throws BookException, UserException;
	public List<Book> searchByAuthor(String key, String author) throws BookException, UserException;
	public Book updateBook(String key,Integer bookId, Book book) throws BookException, UserException;
}
