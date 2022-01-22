package com.epam.ofeitus.library.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigResourceManager {
    private static final Logger logger = LogManager.getLogger(ConfigResourceManager.class);

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    private static final ConfigResourceManager instance = new ConfigResourceManager();

    private static final int DEFAULT_MAX_MEMBER_BOOKS = 5;
    private static final int DEFAULT_LOAN_PERIOD = 30;
    private static final BigDecimal DEFAULT_FINE_RATE = new BigDecimal("0.5");

    private ConfigResourceManager() {
    }

    public static ConfigResourceManager getInstance() {
        return instance;
    }

    public int getMaxMemberBooks() {
        int maxMemberBooks = DEFAULT_MAX_MEMBER_BOOKS;
        try {
            maxMemberBooks = Integer.parseInt(bundle.getString(ConfigParameter.MAX_MEMBER_BOOKS));
        } catch (NumberFormatException | MissingResourceException e) {
            logger.error("Unable to get max member books.", e);
        }
        return maxMemberBooks;
    }

    public int getLoanPeriod() {
        int loanPeriod = DEFAULT_LOAN_PERIOD;
        try {
            loanPeriod = Integer.parseInt(bundle.getString(ConfigParameter.LOAN_PERIOD));
        } catch (NumberFormatException | MissingResourceException e) {
            logger.error("Unable to get loan period.", e);
        }
        return loanPeriod;
    }

    public BigDecimal getFineRate() {
        BigDecimal fineRate = DEFAULT_FINE_RATE;
        try {
            fineRate = new BigDecimal(bundle.getString(ConfigParameter.FINE_RATE));
        } catch (NumberFormatException | MissingResourceException e) {
            logger.error("Unable to get fine rate.", e);
        }
        return fineRate;
    }
}
