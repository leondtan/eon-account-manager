package dev.eon.accountmanager.model.repository;

import dev.eon.accountmanager.entity.User;
import lombok.Data;

@Data
public class BalanceTransactionUserSummary {

    private User user;
    private String type;
    private int value;
    private Double totalCredit;
    private Double totalDebit;

    public BalanceTransactionUserSummary(String type, int value, Double totalCredit, Double totalDebit, User user) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        this.type = type;
        if (type.equals("monthly")) {
            this.type += "(" + months[value] + ")";
        }
        this.value = value;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.user = user;
    }

    public BalanceTransactionUserSummary(int value, Double totalCredit, Double totalDebit, User user) {
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
        this.value = value;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.user = user;
    }


}
