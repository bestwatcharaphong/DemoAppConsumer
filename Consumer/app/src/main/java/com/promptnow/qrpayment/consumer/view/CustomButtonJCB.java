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

public class CustomButtonJCB extends FrameLayout {
    public CustomButtonJCB(@NonNull Context context) {
        super(context);
        initInflate();
    }

    public CustomButtonJCB(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
    }

    public CustomButtonJCB(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomButtonJCB(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.custom_type_jcb, this);
    }

}
