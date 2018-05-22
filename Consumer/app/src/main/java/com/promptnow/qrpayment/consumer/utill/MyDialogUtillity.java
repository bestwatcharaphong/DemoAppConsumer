package com.promptnow.qrpayment.consumer.utill;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.view.SpinView;

/**
 * Created by Suriya on 6/3/2561.
 */

public class MyDialogUtillity {

    public interface OnDialogClickListener {
        void onDialogClickListener(AlertDialog dialog);
    }

    public static void defaultDialog(Context mContext, String title, String message1,
                                     String message2, final OnDialogClickListener listener,
                                     final OnDialogClickListener postListener,
                                     final OnDialogClickListener negaListener,
                                     OnDialogClickListener loadingListener) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mContext);
        View dialogView = View.inflate(mContext, R.layout.dialog_default, null);
        builderDialog.setView(dialogView);

        TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
        TextView dialogMessage1 = dialogView.findViewById(R.id.dialogMessage1);
        TextView dialogMessage2 = dialogView.findViewById(R.id.dialogMessage2);
        final Button dialogBtnOk = dialogView.findViewById(R.id.btnOK);
        Button dialogBtnNo = dialogView.findViewById(R.id.btnNo);
        Button dialogBtnYes = dialogView.findViewById(R.id.btnYes);
        SpinView dialogLoading = dialogView.findViewById(R.id.progressBar);

        final AlertDialog dialog = builderDialog.create();
        dialog.setCancelable(false);
        dialog.show();

        if (title != null) {
            dialogTitle.setText(title);
        } else {
            dialogTitle.setVisibility(View.GONE);
        }
        if (message1 != null) {
            dialogMessage1.setText(message1);
        } else {
            dialogMessage1.setVisibility(View.GONE);
        }
        if (message2 != null) {
            dialogMessage2.setText(message2);
        } else {
            dialogMessage2.setVisibility(View.GONE);
        }
        if (listener == null) {
            dialogBtnOk.setVisibility(View.GONE);
        } else {
            dialogBtnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDialogClickListener(dialog);
                }
            });
        }
        if (postListener != null) {
            dialogBtnNo.setVisibility(View.VISIBLE);
            dialogBtnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postListener.onDialogClickListener(dialog);
                }
            });
        }
        if (negaListener != null) {
            dialogBtnYes.setVisibility(View.VISIBLE);
            dialogBtnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (negaListener != null) {
                        negaListener.onDialogClickListener(dialog);
                    }
                }
            });
        }
        if (loadingListener != null) {
            dialogLoading.setVisibility(View.VISIBLE);
            loadingListener.onDialogClickListener(dialog);
        }
    }

    public static void customDialog(Context mContext, String message, final OnDialogClickListener listener) {

        final AlertDialog.Builder builderDialog = new AlertDialog.Builder(mContext);
        View dialogView = View.inflate(mContext, R.layout.dialog_add_success, null);
        builderDialog.setView(dialogView);

        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        Button btnOk = dialogView.findViewById(R.id.btnOK);

        final AlertDialog dialog = builderDialog.create();
        dialog.show();

        dialogMessage.setText(message);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDialogClickListener(dialog);
                }
            }
        });

    }

}
