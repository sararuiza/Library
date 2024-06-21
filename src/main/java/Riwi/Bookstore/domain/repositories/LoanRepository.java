package Riwi.Bookstore.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Riwi.Bookstore.domain.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
}
