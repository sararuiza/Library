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

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.request.UserUpdateRequest;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.infrastructure.abstrac_service.IUserService;
import Riwi.Bookstore.utils.enums.SortType;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.userService.getAll(page - 1, size, sortType));

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> get(
        @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(
            @Validated
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

     @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(
            @Validated
            @RequestBody UserUpdateRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
