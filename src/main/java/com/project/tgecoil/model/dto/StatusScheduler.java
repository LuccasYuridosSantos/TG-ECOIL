package com.project.tgecoil.model.dto;

public enum StatusScheduler {
    COMPLETADO("COMPLETADO"),
    AGUARDANDO("AGUARDANDO"),
    CANCELADO("CANCELADO"),
    ACEITO("ACEITO");

    private final String status;

    StatusScheduler(final String status) {
        this.status = status;
    }
}
