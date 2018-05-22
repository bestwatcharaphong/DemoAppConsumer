package com.promptnow.qrpayment.consumer.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.promptnow.qrpayment.consumer.R;


/**
 * Created by Suriya on 2/3/2561.
 */

public class CustomButtonQR extends FrameLayout {

    public CustomButtonQR(@NonNull Context context) {
        super(context);
        intiInflate();
    }

    public CustomButtonQR(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intiInflate();
    }

    public CustomButtonQR(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomButtonQR(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intiInflate();
    }

    private void intiInflate(){
        inflate(getContext(), R.layout.custom_button_gen_qr, this);
    }
}
