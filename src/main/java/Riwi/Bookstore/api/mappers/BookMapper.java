package Riwi.Bookstore.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import Riwi.Bookstore.api.dto.request.BookRequest;
import Riwi.Bookstore.api.dto.response.BookResponse;
import Riwi.Bookstore.api.dto.response.LoanBasicResponse;
import Riwi.Bookstore.api.dto.response.ReservationBasicResponse;
import Riwi.Bookstore.domain.entities.Book;
import Riwi.Bookstore.domain.entities.Loan;
import Riwi.Bookstore.domain.entities.Reservation;

@Mapper(componentModel = "spring")

public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "reservations", target = "reservation")
    @Mapping(source = "loans", target = "loan")
    BookResponse bookToBookResponse(Book book);

    // Métodos adicionales para mapear listas y objetos relacionados
    List<ReservationBasicResponse> reservationsToReservationBasicResponseList(List<Reservation> reservations);
    List<LoanBasicResponse> loansToLoanBasicResponseList(List<Loan> loans);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Book requestToEntity(BookRequest request);
    
}
