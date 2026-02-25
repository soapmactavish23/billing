package com.algaworks.algashop.billing.infrastructure.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("algashop.integrations.payment")
public class AlgaShopPaymentProperties {

    @NotNull
    private AlgaShopPaymentProvider provider;

    @NotNull
    @Valid
    private FastpayProperties fastpay;

    private enum AlgaShopPaymentProvider {
        FAKE,
        FASTPAY
    }

    @Data
    @Validated
    public static class FastpayProperties {

        @NotBlank
        private String hostname;

        @NotBlank
        private String privateToken;

    }

}
