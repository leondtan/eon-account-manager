package dev.eon.accountmanager.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CreateBalanceTransactionRequest {
    private UUID userId;

    @NumberFormat
    private Float debit = 0F;

    @NumberFormat
    private Float debitRate = 1F;

    private String debitCurrency = "IDR";

    @NumberFormat
    private Float credit = 0F;

    @NumberFormat
    private Float creditRate = 1F;

    private String creditCurrency = "IDR";

    private String description = "";
}
