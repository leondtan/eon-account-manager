package dev.eon.accountmanager.model.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceTransactionSummary {
    private Double totalCredit;
    private Double totalDebit;
}
