package Riwi.Bookstore.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Bookstore.api.dto.request.LoanRequest;
import Riwi.Bookstore.api.dto.response.LoanResponse;
import Riwi.Bookstore.api.mappers.LoanMapper;
import Riwi.Bookstore.domain.entities.Book;
import Riwi.Bookstore.domain.entities.Loan;
import Riwi.Bookstore.domain.entities.User;
import Riwi.Bookstore.domain.repositories.BookRepository;
import Riwi.Bookstore.domain.repositories.LoanRepository;
import Riwi.Bookstore.domain.repositories.UserRepository;
import Riwi.Bookstore.infrastructure.abstrac_service.ILoanService;
import Riwi.Bookstore.utils.BadRequestException;
import Riwi.Bookstore.utils.enums.SortType;
import Riwi.Bookstore.utils.message.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {

    @Autowired
    private final LoanRepository loanRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final LoanMapper loanMapper;


    @Override
    public LoanResponse create(LoanRequest request) {
        Loan loan = this.loanMapper.requestToEntity(request);

        User user = this.userRepository.findById(request.getUser())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        Book book = this.bookRepository.findById(request.getBook())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("book")));

        loan.setBook(book);
        loan.setUser(user);
        return loanMapper.loantoloaLoanResponse(this.loanRepository.save(loan));
    }

    @Override
    public LoanResponse getById(Long id) {
        return this.loanMapper.loantoloaLoanResponse(this.find(id));
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        Loan loan = this.find(id);

        User user = this.userRepository.findById(request.getUser())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        Book book = this.bookRepository.findById(request.getBook())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("book")));
        
        
        loan.setBook(book);
        loan.setUser(user);
        loan.setId(id);
        
        loan = this.loanMapper.requestToEntity(request);
        return this.loanMapper.loantoloaLoanResponse(this.loanRepository.save(loan));
    }

    @Override
    public void delete(Long id) {
        Loan loan = this.find(id);
        this.loanRepository.delete(loan);
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);
        
        return this.loanRepository.findAll(pagination)
                .map(loan ->  (this.loanMapper.loantoloaLoanResponse(loan)));
    }

    private Loan find(Long id){
        return this.loanRepository.findById(id)
        .orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("loan")));
    }
    
}
