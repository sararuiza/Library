package Riwi.Bookstore.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import Riwi.Bookstore.api.dto.request.ReservationRequest;
import Riwi.Bookstore.api.dto.response.ReservationResponse;
import Riwi.Bookstore.domain.entities.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE= Mappers.getMapper(ReservationMapper.class);

   
    ReservationResponse reservationToReservationResponse(Reservation reservation);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    Reservation requestToEntity(ReservationRequest request);


    @Mapping(target = "book.id", source= "book")
    @Mapping(target = "user.id", source= "user")
    @Mapping(target = "id", ignore = true)
    void updateReservation(ReservationRequest request,
    @MappingTarget Reservation reservation);

    
}
