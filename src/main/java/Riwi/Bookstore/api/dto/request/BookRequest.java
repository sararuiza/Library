package Riwi.Bookstore.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Year of publication is mandatory")
    private Long yearPublication;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotBlank(message = "ISBN is mandatory")

    private String isbn;
    
}
