-- microservices/billing/src/main/resources/db/migration/V0003__add-unique-constraint-invoice-order-id.sql
alter table public.invoice
    add constraint uk_invoice_order_id unique (order_id);