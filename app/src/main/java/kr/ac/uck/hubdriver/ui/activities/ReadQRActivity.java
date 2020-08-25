package kr.ac.uck.hubdriver.ui.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.ActivityReadqrBinding;

/**
 * Created by Jaehyeon on 2020/08/24.
 */
public class ReadQRActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private ActivityReadqrBinding binding;
    private Intent intent;
    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_readqr);

        intent = getIntent();
        Log.d("READ QR Activity Data",intent.getStringExtra("line") + intent.getStringExtra("busNumber") + intent.getStringExtra("time")+ intent.getStringExtra("car"));
        qrCodeReaderView = binding.readQr;

        binding.readQr.setOnQRCodeReadListener(this);
        binding.readQr.setQRDecodingEnabled(true);
        binding.readQr.setBackCamera();
        binding.readQr.setAutofocusInterval(1500L);

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrCodeReaderView.stopCamera();
        Intent intent1 = new Intent(this,MainActivity.class); // 서버와 통신하는 부분 넣기
        startActivity(intent1);

        Log.d("READQR",text + intent.getStringExtra("line") + intent.getStringExtra("busNumber") + intent.getStringExtra("time")+ intent.getStringExtra("car"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
