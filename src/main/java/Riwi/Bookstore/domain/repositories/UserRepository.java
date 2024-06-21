package Riwi.Bookstore.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Riwi.Bookstore.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
