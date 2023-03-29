package dev.eon.accountmanager.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomRateRequest {
    @NotNull
    private Float idrRate;

    @NotNull
    private Float sgdRate;

    @NotNull
    private Float usdRate;
}
