package com.example.central_bank.dto;

import com.example.central_bank.model.ClientInfo;
import com.example.central_bank.model.LoanDecision;
import com.example.central_bank.model.RequestOnCredit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOnCreditDto {
    private Long id;
    private ClientInfo clientInfo;
    private LoanDecision loanDecision;

    public static RequestOnCreditDto fromDto(RequestOnCredit entity) {
        return RequestOnCreditDto.builder()
                .clientInfo(entity.getClientInfo())
                .loanDecision(entity.getLoanDecision())
                .build();
    }

   }
