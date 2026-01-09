package com.project.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.project.model.Investor;

/**
 * Listener that handles {@link InvestorRegisteredEvent} asynchronously.
 */
@Component
public class InvestorEventListener {

    private static final Logger logger = LoggerFactory.getLogger(InvestorEventListener.class);

    @Async
    @EventListener
    public void handleInvestorRegistration(InvestorRegisteredEvent event) {
        Investor investor = event.getInvestor();

        logger.info(">>> EVENT TRIGGERED: New Investor Registered - [ {} {} ({}) ]",
                investor.getFirstName(), investor.getLastName(), investor.getEmail());

        // Simulating a decoupled side-effect like sending a welcome email
        simulateWelcomeEmail(investor);

        // Simulating audit trail entry
        logger.info(">>> AUDIT: Security audit trail updated for investor: {}", investor.getEmail());
    }

    private void simulateWelcomeEmail(Investor investor) {
        logger.info(">>> ACTION: Sending welcome email to {}...", investor.getEmail());
        try {
            // Simulate processing time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("<<< SUCCESS: Welcome email sent successfully to {}!", investor.getEmail());
    }
}
