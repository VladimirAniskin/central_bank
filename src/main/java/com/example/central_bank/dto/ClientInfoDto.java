package com.example.central_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String seriesPassport;
    private String numberPassport;
    private Integer currentMonthlyPayment;
    private Integer currentMonthlySalary;
    private Integer currentZarplata;
    private Boolean goodCreditHistory;
}
