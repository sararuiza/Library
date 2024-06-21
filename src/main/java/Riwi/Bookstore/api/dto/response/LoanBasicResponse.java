package Riwi.Bookstore.api.dto.response;

import java.time.LocalDate;

import Riwi.Bookstore.utils.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanBasicResponse {

    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;
    
}
