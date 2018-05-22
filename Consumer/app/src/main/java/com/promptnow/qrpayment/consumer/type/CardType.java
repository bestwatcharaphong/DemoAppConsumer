package com.promptnow.qrpayment.consumer.type;

import com.promptnow.qrpayment.consumer.R;

import org.parceler.ParcelConstructor;

/**
 * Created by Suriya on 7/3/2561.
 */

@org.parceler.Parcel
public enum CardType {

    JCC(R.string.jcb_premo),
    SMCC(R.string.smcc),
    MIZUHO(R.string.mizuho);

    private final int name;

    @ParcelConstructor
    CardType(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

}
