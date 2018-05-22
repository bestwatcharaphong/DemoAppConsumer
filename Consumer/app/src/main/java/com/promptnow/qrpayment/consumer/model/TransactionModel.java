package com.promptnow.qrpayment.consumer.model;

import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.type.TransactionType;

import org.parceler.Parcel;

/**
 * Created by Suriya on 9/3/2561.
 */

@Parcel
public class TransactionModel {

    int transactionID;
    TransactionType transactionType;
    double amount;
    String merchantName;
    CardType cardType;
    String cardNumber;
    String transactionDate;
    String transactionTime;

    public TransactionModel() {
    }

    public TransactionModel(int transactionID, TransactionType transactionType, double amount, String merchantName, CardType cardType, String cardNumber, String transactionDate, String transactionTime) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.merchantName = merchantName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
