package Riwi.Bookstore.api.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import Riwi.Bookstore.api.dto.request.LoanRequest;
import Riwi.Bookstore.api.dto.response.LoanResponse;
import Riwi.Bookstore.domain.entities.Loan;



@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanResponse loantoloaLoanResponse(Loan loan);

   @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "user.id", source = "user"),
        @Mapping(target = "book.id", source= "book")
    })
    Loan requestToEntity(LoanRequest request);

    
    
}
