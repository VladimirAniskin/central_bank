package com.example.central_bank.service;

import com.example.central_bank.dto.ClientInfoDto;
import com.example.central_bank.dto.RequestOnCreditDto;
import org.springframework.http.ResponseEntity;

public interface CreditService {
    ResponseEntity<RequestOnCreditDto> createRequest(ClientInfoDto dto);
}
