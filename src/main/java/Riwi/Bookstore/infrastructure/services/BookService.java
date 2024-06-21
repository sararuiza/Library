package Riwi.Bookstore.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Bookstore.api.dto.request.BookRequest;
import Riwi.Bookstore.api.dto.response.BookResponse;
import Riwi.Bookstore.api.dto.response.LoanResponse;
import Riwi.Bookstore.api.mappers.BookMapper;
import Riwi.Bookstore.domain.entities.Book;
import Riwi.Bookstore.domain.entities.Loan;
import Riwi.Bookstore.domain.repositories.BookRepository;
import Riwi.Bookstore.infrastructure.abstrac_service.IBookService;
import Riwi.Bookstore.utils.BadRequestException;
import Riwi.Bookstore.utils.enums.SortType;
import Riwi.Bookstore.utils.message.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookMapper bookMapper;

    @Override
    public BookResponse create(BookRequest request) {
        Book book = this.bookMapper.requestToEntity(request);

        return bookMapper.bookToBookResponse(this.bookRepository.save(book));
    }

    @Override
    public BookResponse getById(Long id) {
        return this.bookMapper.bookToBookResponse(this.find(id));
    }

    @Override
    public BookResponse update(BookRequest request, Long id) {
        Book book = this.find(id);
        book = this.bookMapper.requestToEntity(request);
        return this.bookMapper.bookToBookResponse(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
         Book book = this.find(id);
        this.bookRepository.delete(book);
    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sortType) {
         
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);
        
        return this.bookRepository.findAll(pagination)
                .map(book ->  (this.bookMapper.bookToBookResponse(book)));
    }


    private Book find(Long id){
        return this.bookRepository.findById(id)
        .orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("book")));
    }
    
}
