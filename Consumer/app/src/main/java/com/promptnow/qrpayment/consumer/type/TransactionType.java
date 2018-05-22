package com.promptnow.qrpayment.consumer.type;

import com.promptnow.qrpayment.consumer.R;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Suriya on 9/3/2561.
 */
@Parcel
public enum TransactionType {

    PAYMENY(R.string.payment_type);

    private final int name;

    @ParcelConstructor
    TransactionType(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }
}
