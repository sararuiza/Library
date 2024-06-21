package Riwi.Bookstore.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Riwi.Bookstore.api.dto.request.BookRequest;
import Riwi.Bookstore.api.dto.request.LoanRequest;
import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.response.BookResponse;
import Riwi.Bookstore.api.dto.response.LoanResponse;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.infrastructure.abstrac_service.IBookService;
import Riwi.Bookstore.utils.enums.SortType;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/books")
@AllArgsConstructor
public class BookController {

    @Autowired
    private final IBookService bookService;

     @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.bookService.getAll(page - 1, size, sortType));

    }

     @GetMapping(path = "/{id}")
    public ResponseEntity<BookResponse> get(
        @PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.getById(id));
    }

     @PostMapping
    public ResponseEntity<BookResponse> insert(
            @Validated
            @RequestBody BookRequest request) {
        return ResponseEntity.ok(this.bookService.create(request));
    }

     @PutMapping(path = "/{id}")
    public ResponseEntity<BookResponse> update(
            @Validated
            @RequestBody BookRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.update(request, id));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }


    
}
