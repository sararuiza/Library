package Riwi.Bookstore.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Riwi.Bookstore.domain.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
