package com.promptnow.qrpayment.consumer.model;

import com.promptnow.qrpayment.consumer.type.CardType;

import org.parceler.Parcel;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Suriya on 3/3/2561.
 */

@Parcel
public class AccountCard {

    String accountCardName;
    String accountCradNo;
    String accountCardDate;
    AccountType type;
    CardType cardType;
    int accountCardBalance;
    int authentication;

    public AccountCard() {
    }

    public String getAccountCardName() {
        return accountCardName;
    }

    public void setAccountCardName(String accountCardName) {
        this.accountCardName = accountCardName;
    }

    public String getAccountCradNo() {
        return accountCradNo;
    }

    public void setAccountCradNo(String accountCradNo) {
        this.accountCradNo = accountCradNo;
    }

    public String getAccountCardDate() {
        return accountCardDate;
    }

    public void setAccountCardDate(String accountCardDate) {
        this.accountCardDate = accountCardDate;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getAccountCardBalance() {
        return accountCardBalance;
    }

    public void setAccountCardBalance(int accountCardBalance) {
        this.accountCardBalance = accountCardBalance;
    }

    public int getAuthentication() {
        return authentication;
    }

    public void setAuthentication(int authentication) {
        this.authentication = authentication;
    }
}
