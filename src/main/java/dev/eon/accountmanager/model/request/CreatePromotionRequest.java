package dev.eon.accountmanager.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionRequest {
    private String title;
    private Float balanceThreshold;
    private String periodType;
    private String periodValue;
}
