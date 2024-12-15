package com.example.central_bank.service;

import com.example.central_bank.dto.ClientInfoDto;
import com.example.central_bank.dto.RequestOnCreditDto;
import com.example.central_bank.model.ClientInfo;
import com.example.central_bank.model.RequestOnCredit;
import com.example.central_bank.repository.ClientInfoRepository;
import com.example.central_bank.repository.LoanDecisionRepository;
import com.example.central_bank.repository.RequestOnCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class CreditServiceImpl implements CreditService{
    private final ClientInfoRepository clientInfoRepository;
    private final LoanDecisionRepository loanDecisionRepository;
    private final RequestOnCreditRepository requestOnCreditRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CreditServiceImpl(
            ClientInfoRepository clientInfoRepository,
            LoanDecisionRepository loanDecisionRepository,
            RequestOnCreditRepository requestOnCreditRepository
    ) {
        this.clientInfoRepository = clientInfoRepository;
        this.loanDecisionRepository = loanDecisionRepository;
        this.requestOnCreditRepository = requestOnCreditRepository;
    }

    @Override
    public ResponseEntity<RequestOnCreditDto> createRequest(ClientInfoDto dto) {
        ClientInfo clientInfo;

        //Есть ли человек в базе ЦБ (т.е. есть ли у него кредиты?)?
        if (dto.getId() == null) {
            clientInfo = ClientInfo.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .patronymic(dto.getPatronymic())
                    .numberPassport(dto.getNumberPassport())
                    .seriesPassport(dto.getSeriesPassport())
                    .currentZarplata(dto.getCurrentZarplata())
                    .currentMonthlyPayment(dto.getCurrentMonthlyPayment())
                    .currentMonthlySalary(dto.getCurrentMonthlySalary())
                    .goodCreditHistory(dto.getGoodCreditHistory())
                    .build();

            clientInfo = clientInfoRepository.save(clientInfo);
        } else {
            Optional<RequestOnCredit> requestOnCredit = requestOnCreditRepository.findByClientInfo_Id(dto.getId());
            if (requestOnCredit.isPresent()) {
                return ResponseEntity.ok(RequestOnCreditDto.fromDto(requestOnCredit.get()));
            }

            clientInfo = clientInfoRepository.findById(dto.getId()).orElseThrow();
        }

        RequestOnCreditDto result = new RequestOnCreditDto();
        result.setClientInfo(clientInfo);
        return restTemplate.postForEntity("http://localhost:8082/decision", result, RequestOnCreditDto.class);
    }
}
