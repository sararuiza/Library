package Riwi.Bookstore.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookBasicResponse {

    private Long id;
    private String title;
    private String author;
    private Long yearPublication;
    private String genere;
    private String isbn;
    
}
