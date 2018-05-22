package com.promptnow.qrpayment.consumer.utill;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Suriya on 4/3/2561.
 */

public class MoneyTextView extends AppCompatTextView {

    String rawText;

    public MoneyTextView(Context context) {
        super(context);
    }

    public MoneyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoneyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        rawText = text.toString();
        String prezzo = text.toString().replaceAll("[+-]","").trim();
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            prezzo = decimalFormat.format(Double.parseDouble(text.toString()));
        }catch (Exception e){}

        super.setText(prezzo, type);
    }

    @Override
    public CharSequence getText() {
        return rawText;
    }
}
