package Riwi.Bookstore.infrastructure.abstrac_service;

import Riwi.Bookstore.api.dto.request.ReservationRequest;
import Riwi.Bookstore.api.dto.response.ReservationResponse;

public interface IReservationService extends CrudService<ReservationRequest,ReservationResponse,Long> {
    
    public final String FIELD_BY_SORT = "reservationDate";

}
