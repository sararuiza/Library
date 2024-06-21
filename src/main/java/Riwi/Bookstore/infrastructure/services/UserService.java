package Riwi.Bookstore.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.api.mappers.UserMapper;
import Riwi.Bookstore.domain.entities.User;
import Riwi.Bookstore.domain.repositories.UserRepository;
import Riwi.Bookstore.infrastructure.abstrac_service.IUserService;
import Riwi.Bookstore.utils.BadRequestException;
import Riwi.Bookstore.utils.enums.SortType;
import Riwi.Bookstore.utils.message.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;
    
    @Override
    public UserResponse create(UserRequest request) {
        User user = this.userMapper.requestToEntity(request);

        return userMapper.userToUserResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse getById(Long id) {
        return this.userMapper.userToUserResponse(this.find(id));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = this.find(id);
        user = this.userMapper.requestToEntity(request);
        return this.userMapper.userToUserResponse(this.userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = this.find(id);
        this.userRepository.delete(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {

        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);
        
        return this.userRepository.findAll(pagination)
                .map(user ->  (this.userMapper.userToUserResponse(user)));
    }


    private User find(Long id){
        return this.userRepository.findById(id)
        .orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("user")));
    }
    
}
