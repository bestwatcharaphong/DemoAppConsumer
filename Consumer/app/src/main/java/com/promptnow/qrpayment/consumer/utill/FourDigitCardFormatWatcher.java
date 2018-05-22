package com.promptnow.qrpayment.consumer.utill;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Created by Suriya on 3/3/2561.
 */

public class FourDigitCardFormatWatcher implements TextWatcher {

    private static final char space = ' ';

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        int pos = 0;
        while (true) {
            if (pos >= s.length()) break;
            if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                s.delete(pos, pos + 1);
            } else {
                pos++;
            }
        }

        // Insert char where needed.
        pos = 4;
        while (true) {
            if (pos >= s.length()) break;
            final char c = s.charAt(pos);
            // Only if its a digit where there should be a space we insert a space
            if ("0123456789".indexOf(c) >= 0) {
                s.insert(pos, "" + space);
            }
            pos += 5;
        }
    }
}
