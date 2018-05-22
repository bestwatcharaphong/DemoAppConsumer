package com.promptnow.qrpayment.consumer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.promptnow.qrpayment.consumer.type.CardType;

/**
 * Created by Suriya on 2/3/2561.
 */

public class TypeCard implements Parcelable {

    CardType typeCard;
    String cardName;

    public TypeCard(){

    }

    public TypeCard(CardType typeCard, String cardName) {
        this.typeCard = typeCard;
        this.cardName = cardName;
    }

    protected TypeCard(Parcel in) {
        cardName = in.readString();
    }

    public static final Creator<TypeCard> CREATOR = new Creator<TypeCard>() {
        @Override
        public TypeCard createFromParcel(Parcel in) {
            return new TypeCard(in);
        }

        @Override
        public TypeCard[] newArray(int size) {
            return new TypeCard[size];
        }
    };

    public CardType getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(CardType typeCard) {
        this.typeCard = typeCard;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardName);
    }
}
