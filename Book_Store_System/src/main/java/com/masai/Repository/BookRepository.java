package com.masai.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1")
    public Optional<List<Book>> findAllTheBooks();

    @Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND b.ISBN = :isbn")
    public Optional<Book> findBookByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND b.title = :title")
    public Optional<List<Book>> findBooksByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.availabilityStatus = 1 AND b.author = :author")
    public Optional<List<Book>> findBooksByAuthor(@Param("author") String author);

}
