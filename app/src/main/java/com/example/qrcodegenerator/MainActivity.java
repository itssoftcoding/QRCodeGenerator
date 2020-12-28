package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGenerate = findViewById(R.id.btnGenerate);
        EditText etText = findViewById(R.id.etText);
        ImageView imageCode = findViewById(R.id.imageCode);

        btnGenerate.setOnClickListener(v -> {

            String myText = etText.getText().toString().trim();

            MultiFormatWriter mWriter = new MultiFormatWriter();

            try {
                BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);

                BarcodeEncoder mEncoder = new BarcodeEncoder();
                Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
                imageCode.setImageBitmap(mBitmap);

                // to hide the keyboard
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);

            } catch (WriterException e) {
                e.printStackTrace();
            }

        });

    }
}