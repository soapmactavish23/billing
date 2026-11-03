package com.algaworks.algashop.billing.application.invoice.reply;

import com.algaworks.algashop.billing.application.OutboundIntegrationReply;

public interface InvoiceIntegrationReplyPublisher {
    void send(String replyTopic, String correlationId, OutboundIntegrationReply reply);
}
