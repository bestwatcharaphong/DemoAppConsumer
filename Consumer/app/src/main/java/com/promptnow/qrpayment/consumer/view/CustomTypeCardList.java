package com.promptnow.qrpayment.consumer.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.type.CardType;

/**
 * Created by Suriya on 2/3/2561.
 */

public class CustomTypeCardList extends FrameLayout {

    private ImageView imgIcLogo;
    private TextView tvLogo;

    public CustomTypeCardList(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public CustomTypeCardList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public CustomTypeCardList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTypeCardList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInflate(){
        inflate(getContext(), R.layout.custom_type_card_list, this);
    }

    private void initInstance(){

        imgIcLogo = findViewById(R.id.imgIcLogo);
        tvLogo = findViewById(R.id.tvLogo);

    }

    public void setType(CardType type, String typeName){
        switch (type){
            case JCC:
                imgIcLogo.setImageResource(R.drawable.ic_wallet_logo1);
                tvLogo.setText(typeName);
                break;
            case SMCC:
                imgIcLogo.setImageResource(R.drawable.ic_wallet_logo2);
                tvLogo.setText(typeName);
                break;
            case MIZUHO:
                imgIcLogo.setImageResource(R.drawable.ic_wallet_logo3);
                tvLogo.setText(typeName);
                break;
        }
    }

}
