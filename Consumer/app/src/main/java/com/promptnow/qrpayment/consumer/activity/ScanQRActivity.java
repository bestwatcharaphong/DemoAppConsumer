package com.promptnow.qrpayment.consumer.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.promptnow.qrpayment.consumer.R;
import com.promptnow.qrpayment.consumer.model.TransactionModel;
import com.promptnow.qrpayment.consumer.type.CardType;
import com.promptnow.qrpayment.consumer.type.TransactionType;
import com.promptnow.qrpayment.consumer.utill.MyDialogUtillity;

import org.parceler.Parcels;

import java.io.IOException;

public class ScanQRActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton btnBack;
    private SurfaceView cameraView;
    private BarcodeDetector barcode;
    private CameraSource cameraSource;
    private SurfaceHolder holder;
    private TextView tvScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        initInstance();

    }

    private void initInstance() {

        toolbar = findViewById(R.id.toolbar);
        btnBack = toolbar.findViewById(R.id.btnBack);
        cameraView = findViewById(R.id.cameraPre);
        btnBack.setOnClickListener(this);
        cameraView.setZOrderMediaOverlay(true);
        tvScan = findViewById(R.id.tvScan);
        holder = cameraView.getHolder();
        barcode = new BarcodeDetector.Builder(ScanQRActivity.this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        if (!barcode.isOperational()) {
            showToast("Sorry, Couldn't setup the detector");
            finish();
        }
        cameraSource = new CameraSource.Builder(ScanQRActivity.this, barcode)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(640, 480)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(ScanQRActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ScanQRActivity.this,
                            new String[]{Manifest.permission.CAMERA}, 1001);
                    return;
                }
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcode.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodeArray = detections.getDetectedItems();
                if (barcodeArray.size() != 0) {
                    tvScan.post(new Runnable() {
                        @Override
                        public void run() {
                            successDialog(barcodeArray);
                            cameraSource.stop();
                        }
                    });
                }
            }
        });

    }

    private void successDialog(final SparseArray<Barcode> barcodeArray) {
        MyDialogUtillity.defaultDialog(ScanQRActivity.this, "Loading", "Merchant has sacn QR code completely",
                "Please wait for processing", null, null, null, new MyDialogUtillity.OnDialogClickListener() {
                    @Override
                    public void onDialogClickListener(final AlertDialog dialog) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TransactionModel transactionModel = new TransactionModel(158975,
                                        TransactionType.PAYMENY, +1500, "JR Pass", CardType.JCC,
                                        "4589458945894589", "2018-02-28", "12:34:00");
                                Intent intent = new Intent();
                                intent.putExtra("Transaction", Parcels.wrap(transactionModel));
                                setResult(RESULT_OK, intent);
                                dialog.dismiss();
                                finish();
                            }
                        }, 5000);

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(ScanQRActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
