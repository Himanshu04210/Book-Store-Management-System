package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1")
	List<Book> findAllBooks();
	
	@Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND isbn = ?1")
	Book findByIsbn(String isbn);

	@Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND title = ?1")
	List<Book> findByTitle(String title);
	

	@Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND author = ?1")
	List<Book> findByAuthor(String author);
	
}
