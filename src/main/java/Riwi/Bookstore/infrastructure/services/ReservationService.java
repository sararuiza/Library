package Riwi.Bookstore.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Riwi.Bookstore.api.dto.request.ReservationRequest;
import Riwi.Bookstore.api.dto.response.ReservationResponse;
import Riwi.Bookstore.api.mappers.ReservationMapper;
import Riwi.Bookstore.domain.entities.Book;
import Riwi.Bookstore.domain.entities.Reservation;
import Riwi.Bookstore.domain.entities.User;
import Riwi.Bookstore.domain.repositories.BookRepository;
import Riwi.Bookstore.domain.repositories.ReservationRepository;
import Riwi.Bookstore.domain.repositories.UserRepository;
import Riwi.Bookstore.infrastructure.abstrac_service.IReservationService;
import Riwi.Bookstore.utils.BadRequestException;
import Riwi.Bookstore.utils.enums.SortType;
import Riwi.Bookstore.utils.message.ErrorMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    
    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final ReservationMapper reservationMapper;
    
    @Override
    public ReservationResponse create(ReservationRequest request) {
        Reservation reservation = this.reservationMapper.requestToEntity(request);

        User user = this.userRepository.findById(request.getUser())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        Book book = this.bookRepository.findById(request.getBook())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("book")));

        reservation.setBook(book);
        reservation.setUser(user);
        return reservationMapper.reservationToReservationResponse(this.reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse getById(Long id) {
        return this.reservationMapper.reservationToReservationResponse(this.find(id));
    }

    @Override
    public ReservationResponse update(ReservationRequest request, Long id) {

        Reservation reservation = this.find(id);
         reservation = this.reservationMapper.requestToEntity(request);
        // this.reservationMapper.updateReservation(request, this.reservationRepository.save(reservation));


        User user = this.userRepository.findById(request.getUser())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        Book book = this.bookRepository.findById(request.getBook())
        .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("book")));
        
        
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setId(id);

         return this.reservationMapper.reservationToReservationResponse(reservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.find(id);
        this.reservationRepository.delete(reservation); 
    }

    @Override
    public Page<ReservationResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        pagination = PageRequest.of(page, size);
        
        return this.reservationRepository.findAll(pagination)
                .map(reservation ->  (this.reservationMapper.reservationToReservationResponse(reservation)));
    }

    private Reservation find(Long id){
        return this.reservationRepository.findById(id)
        .orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("reservation")));
    }
    
}
