package dev.eon.accountmanager.model.response;

import dev.eon.accountmanager.entity.BalanceTransaction;
import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.model.request.CustomRateRequest;
import lombok.Data;

import java.util.UUID;

@Data
public class BalanceTransactionCustomRateResponse {
    private UUID id;

    private User user;

    private Float debit;

    private Float debitRate;

    private Float customDebitRate;

    private Float customDebit;

    private String debitCurrency;

    private Float credit;

    private Float creditRate;

    private String creditCurrency;

    private Float customCreditRate;

    private Float customCredit;

    private String description;

    public BalanceTransactionCustomRateResponse(BalanceTransaction balanceTransaction, CustomRateRequest customRate) {
        this.id = balanceTransaction.getId();
        this.credit = balanceTransaction.getCredit();
        this.creditRate = balanceTransaction.getCreditRate();
        this.creditCurrency = balanceTransaction.getCreditCurrency();
        this.debit = balanceTransaction.getDebit();
        this.debitRate = balanceTransaction.getDebitRate();
        this.debitCurrency = balanceTransaction.getDebitCurrency();
        this.description = balanceTransaction.getDescription();
        this.user = balanceTransaction.getUser();

        if (balanceTransaction.getDebitCurrency().equalsIgnoreCase("IDR")) {
            this.customDebitRate = customRate.getIdrRate();
        } else if (balanceTransaction.getDebitCurrency().equalsIgnoreCase("SGD")) {
            this.customDebitRate = customRate.getSgdRate();
        } else if (balanceTransaction.getDebitCurrency().equalsIgnoreCase("USD")) {
            this.customDebitRate = customRate.getUsdRate();
        } else {
            this.customDebitRate = 1F;
        }
        this.customDebit = this.debit * this.customDebitRate;

        if (balanceTransaction.getCreditCurrency().equalsIgnoreCase("IDR")) {
            this.customCreditRate = customRate.getIdrRate();
        } else if (balanceTransaction.getCreditCurrency().equalsIgnoreCase("SGD")) {
            this.customCreditRate = customRate.getSgdRate();
        } else if (balanceTransaction.getCreditCurrency().equalsIgnoreCase("USD")) {
            this.customCreditRate = customRate.getUsdRate();
        } else {
            this.customCreditRate = 1F;
        }
        this.customCredit = this.credit * this.customCreditRate;
    }
}
