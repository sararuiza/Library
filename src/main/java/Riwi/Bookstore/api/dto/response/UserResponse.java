package Riwi.Bookstore.api.dto.response;

import java.util.List;

import Riwi.Bookstore.utils.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private UserRole role;

    private List<ReservationBasicResponse> reservation;
    private List<LoanBasicResponse> loan;
    
}
