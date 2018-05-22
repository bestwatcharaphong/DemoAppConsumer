package com.promptnow.qrpayment.consumer.item;

import com.promptnow.qrpayment.consumer.Base.BaseDetailItem;
import com.promptnow.qrpayment.consumer.type.DetailType;

/**
 * Created by Suriya on 9/3/2561.
 */

public class DateDetailItem extends BaseDetailItem{

    String date;

    public DateDetailItem() {
        super(DetailType.TYPE_HEAD);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
