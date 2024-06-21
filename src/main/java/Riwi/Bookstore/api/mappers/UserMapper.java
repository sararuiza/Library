package Riwi.Bookstore.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import Riwi.Bookstore.api.dto.request.UserRequest;
import Riwi.Bookstore.api.dto.response.LoanBasicResponse;
import Riwi.Bookstore.api.dto.response.ReservationBasicResponse;
import Riwi.Bookstore.api.dto.response.UserResponse;
import Riwi.Bookstore.domain.entities.Loan;
import Riwi.Bookstore.domain.entities.Reservation;
import Riwi.Bookstore.domain.entities.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "reservations", target = "reservation")
    @Mapping(source = "loans", target = "loan")
    UserResponse userToUserResponse(User user);

    // Métodos adicionales para mapear listas y objetos relacionados
    List<ReservationBasicResponse> toReservationBasicResponseList(List<Reservation> reservations);
    List<LoanBasicResponse> toLoanBasicResponseList(List<Loan> loans);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    User requestToEntity(UserRequest request);


    
    
}
