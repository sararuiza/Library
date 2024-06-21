package Riwi.Bookstore.infrastructure.abstrac_service;

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long> {

    public final String FIELD_BY_SORT = "username";
    
}
