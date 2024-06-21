package Riwi.Bookstore.api.dto.request;

import java.time.LocalDate;
import Riwi.Bookstore.utils.enums.LoanStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Loan date is mandatory")
    private LocalDate loanDate;

    @NotNull(message = "Return date is mandatory")
    private LocalDate returnDate;

    @NotNull(message = "Loan status is mandatory")
    private LoanStatus status;

    @NotNull(message = "User ID is mandatory")
    private Long user;

    @NotNull(message = "Book ID is mandatory")
    private Long book;

}
