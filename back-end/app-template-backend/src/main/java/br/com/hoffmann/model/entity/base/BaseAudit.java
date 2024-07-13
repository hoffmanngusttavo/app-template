package br.com.hoffmann.model.entity.base;


import java.time.LocalDateTime;

public interface BaseAudit {

    String getCreatedBy();

    LocalDateTime getCreated();

    String getLastUpdateBy();

    LocalDateTime getUpdated();

}
