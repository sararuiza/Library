package Riwi.Bookstore.infrastructure.abstrac_service;

import org.springframework.data.domain.Page;

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.request.UserUpdateRequest;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.utils.enums.SortType;

public interface IUserService {

    public final String FIELD_BY_SORT = "username";

    public UserResponse create(UserRequest request);

    public UserResponse getById(Long id);

    public UserResponse update(UserUpdateRequest request, Long id);

    public void delete(Long id);

    public Page<UserResponse> getAll(int page, int size, SortType sortType);
    
}
