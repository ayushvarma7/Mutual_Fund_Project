package com.project.event;

import org.springframework.context.ApplicationEvent;
import com.project.model.Investor;

/**
 * Event published when a new investor is successfully registered.
 */
public class InvestorRegisteredEvent extends ApplicationEvent {

    private final Investor investor;

    public InvestorRegisteredEvent(Object source, Investor investor) {
        super(source);
        this.investor = investor;
    }

    public Investor getInvestor() {
        return investor;
    }
}
