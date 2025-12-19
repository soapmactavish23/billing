package com.algaworks.algashop.billing.domain.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditableEntity {

    @CreatedBy
    protected UUID createdByUserId;

    @CreatedDate
    protected OffsetDateTime createdAt;

    @LastModifiedBy
    protected UUID lastModifiedByUserId;

    @LastModifiedDate
    private OffsetDateTime lastModifiedDate;

    @Version
    private long version;

}
