package com.example.cinema_spring_app.model;

import java.math.BigDecimal;
import java.util.EnumSet;

public enum TicketOption {

    NORMAL (new BigDecimal(1.0)),
    STUDENT (new BigDecimal(0.5)),
    KID (new BigDecimal(0.5)),
    BIG_GROUP (new BigDecimal(0.7));

    private final BigDecimal factor;

    TicketOption(BigDecimal factor) {
        this.factor = factor;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public static final EnumSet<TicketOption> options = EnumSet.allOf(TicketOption.class);

    public static EnumSet<TicketOption> getOptions() {
        return options;
    }
}
