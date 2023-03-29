package dev.eon.accountmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eon.accountmanager.entity.base.Auditable;
import dev.eon.accountmanager.model.request.CreateBalanceTransactionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "balance_transactions")
@Data
@NoArgsConstructor
public class BalanceTransaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Column(name = "debit")
    private Float debit = 0F;

    @Column(name = "debit_rate")
    private Float debitRate = 1F;

    @Column(name = "debit_currency")
    private String debitCurrency = "IDR";

    @Column(name = "credit")
    private Float credit = 0F;

    @Column(name = "credit_rate")
    private Float creditRate = 1F;

    @Column(name = "credit_currency")
    private String creditCurrency = "IDR";

    @Column(name = "description")
    private String description = "General Transaction";

    public BalanceTransaction(
            User user,
            Float debit,
            Float debitRate,
            String debitCurrency,
            Float credit,
            Float creditRate,
            String creditCurrency,
            String description
    ) {
        this.user = user;
        this.debit = debit;
        this.debitRate = debitRate;
        this.debitCurrency = debitCurrency;
        this.credit = credit;
        this.creditRate = creditRate;
        this.creditCurrency = creditCurrency;
        this.description = description;
    }

    public BalanceTransaction(
            User user,
            CreateBalanceTransactionRequest request
    ) {
        this.user = user;
        this.debit = Objects.requireNonNullElse(request.getDebit(), this.debit);
        this.debitRate =  Objects.requireNonNullElse(request.getDebitRate(), this.debitRate);
        this.debitCurrency = Objects.requireNonNullElse(request.getDebitCurrency(), this.debitCurrency);
        this.credit = Objects.requireNonNullElse(request.getCredit(), this.credit);
        this.creditRate = Objects.requireNonNullElse(request.getCreditRate(), this.creditRate);
        this.creditCurrency = Objects.requireNonNullElse(request.getCreditCurrency(), this.creditCurrency);
        this.description = Objects.requireNonNullElse(request.getDescription(), this.description);
    }
}
