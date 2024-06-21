package Riwi.Bookstore.infrastructure.abstrac_service;

import Riwi.Bookstore.api.dto.request.BookRequest;
import Riwi.Bookstore.api.dto.response.BookResponse;

public interface IBookService extends CrudService<BookRequest, BookResponse, Long> {
    
    public final String FIELD_BY_SORT = "title";
}
