
package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.VerificacionToken;


public interface IVerificacionToken {
    
    Result<VerificacionToken> addToken(VerificacionToken token);
    Result<VerificacionToken> verifyToken(String token);
    Result<VerificacionToken> deleteToken(VerificacionToken token);
    
    
    
}
