package com.promptnow.qrpayment.consumer.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.promptnow.qrpayment.consumer.R;

/**
 * Created by Suriya on 13/3/2561.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public SimpleDividerItemDecoration(Context context) {
        mDrawable = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int botton = top + mDrawable.getIntrinsicHeight();

            mDrawable.setBounds(left, top, right, botton);
            mDrawable.draw(c);
        }
    }
}
