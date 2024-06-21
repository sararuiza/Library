package Riwi.Bookstore.infrastructure.abstrac_service;

import Riwi.Bookstore.api.dto.request.LoanRequest;
import Riwi.Bookstore.api.dto.response.LoanResponse;

public interface ILoanService extends CrudService<LoanRequest, LoanResponse, Long> {
    
    public final String FIELD_BY_SORT = "loanDate";
}
