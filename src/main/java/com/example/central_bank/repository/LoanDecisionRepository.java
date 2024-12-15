package com.example.central_bank.repository;

import com.example.central_bank.model.LoanDecision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDecisionRepository extends JpaRepository<LoanDecision, Long> {
}
