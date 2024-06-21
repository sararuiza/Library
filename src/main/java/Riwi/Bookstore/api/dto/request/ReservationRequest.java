package Riwi.Bookstore.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @NotNull(message = "Reservation date is mandatory")
    private LocalDate reservationDate;

    @NotNull(message = "Status is mandatory")
    private String status;

    @NotNull(message = "User ID is mandatory")
    @Positive(message = "User ID must be positive")
    private Long user;

    @NotNull(message = "Book ID is mandatory")
    @Positive(message = "Book ID must be positive")
    private Long book;


}
