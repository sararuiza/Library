package Riwi.Bookstore.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import org.mapstruct.Mappings;

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.request.UserUpdateRequest;
import Riwi.Bookstore.api.dto.response.LoanBasicResponse;
import Riwi.Bookstore.api.dto.response.ReservationBasicResponse;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.domain.entities.Loan;
import Riwi.Bookstore.domain.entities.Reservation;
import Riwi.Bookstore.domain.entities.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    
    // @Mapping(source = "reservations", target = "reservation")
    // @Mapping(source = "loans", target = "loan")
    UserResponse userToUserResponse(User user);

    // Métodos adicionales para mapear listas y objetos relacionados
    List<ReservationBasicResponse> toReservationBasicResponseList(List<Reservation> reservations);
    List<LoanBasicResponse> toLoanBasicResponseList(List<Loan> loans);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    User requestToEntity(UserRequest request);


    // @Mapping(target = "book.id", source= "book")
    // @Mapping(target = "user.id", source= "user")
    // @Mapping(target = "id", ignore = true)
    // void updateReservation(ReservationRequest request,
    // @MappingTarget Reservation reservation);

    @Mappings({
        @Mapping(target = "loans", ignore = true),
        @Mapping(target = "reservations", ignore = true),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "id", ignore = true)
    })
    void updateUser(UserUpdateRequest request,
    @MappingTarget User user);





    
    
}
