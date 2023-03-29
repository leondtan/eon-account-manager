package dev.eon.accountmanager.model.repository;

import lombok.Data;

@Data
public class BalanceTransactionSpecificSummary {
    private String type;
    private String value;
    private Double totalCredit;
    private Double totalDebit;

    public BalanceTransactionSpecificSummary(String type, int value, Double totalCredit, Double totalDebit) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        this.type = type;
        if (type.equals("monthly")) {
            try {
                this.value = months[value - 1];
            } catch (Exception e) {
                this.value = String.valueOf(value);
            }
        } else {
            this.value = String.valueOf(value);
        }
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
    }

    public BalanceTransactionSpecificSummary(int value, Double totalCredit, Double totalDebit) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

//        this.type = type;
//        if (type.equals("month")) {
//            try {
//                this.value = months[Integer.parseInt(value)];
//            } catch (Exception e) {
//                this.value = value;
//            }
//        } else {
//        }
        this.value = String.valueOf(value);
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
    }


}
