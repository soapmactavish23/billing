package com.algaworks.algashop.billing.infrastructure.creditcard.fastpay;

import com.algaworks.algashop.billing.domain.model.creditcard.CreditCardProviderService;
import com.algaworks.algashop.billing.domain.model.creditcard.LimitedCreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "algashop.integrations.payment.provider", havingValue = "FASTPAY")
public class CredtiCardProviderServiceFastpayImpl implements CreditCardProviderService {

    private final FastpayCreditCardAPIClient fastpayCreditCardAPIClient;

    @Override
    public LimitedCreditCard register(UUID customerId, String tokenizedCard) {
        FastpayCreditCardInput input = FastpayCreditCardInput.builder()
                .tokenizedCard(tokenizedCard)
                .customerCode(customerId.toString())
                .build();

        FastpayCreditCardResponse response = fastpayCreditCardAPIClient.create(input);

        return toLimitedCredtCard(response);
    }

    @Override
    public Optional<LimitedCreditCard> findById(String gatewayCode) {
        FastpayCreditCardResponse response = fastpayCreditCardAPIClient.findById(gatewayCode);
        return Optional.of(toLimitedCredtCard(response));
    }

    @Override
    public void delete(String gatewayCode) {
        fastpayCreditCardAPIClient.delete(gatewayCode);
    }

    private static LimitedCreditCard toLimitedCredtCard(FastpayCreditCardResponse response) {
        return LimitedCreditCard.builder()
                .brand(response.getBrand())
                .expYear(response.getExpYear())
                .expMonth(response.getExpMonth())
                .lastNumbers(response.getLastNumbers())
                .gatewayCode(response.getId())
                .build();
    }
}
