package Riwi.Bookstore.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Long yearPublication;
    private String genere;
    private String isbn;


    private List<ReservationBasicResponse> reservation;
    private List<LoanBasicResponse> loan;
    
}
