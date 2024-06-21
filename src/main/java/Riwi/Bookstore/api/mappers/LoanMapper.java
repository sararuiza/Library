package Riwi.Bookstore.api.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import Riwi.Bookstore.api.dto.request.LoanRequest;
import Riwi.Bookstore.api.dto.response.LoanResponse;
import Riwi.Bookstore.domain.entities.Loan;



@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanResponse loantoloaLoanResponse(Loan loan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    Loan requestToEntity(LoanRequest request);

    
    
}
