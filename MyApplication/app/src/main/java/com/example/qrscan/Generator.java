package com.example.qrscan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Generator extends AppCompatActivity {

    EditText text;
    Button gen_but;
    ImageView image;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        text= (EditText)findViewById(R.id.qr_text);
        gen_but = (Button)findViewById(R.id.gen);
        image = (ImageView)findViewById(R.id.image);
        gen_but.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                text2Qr = text.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }
                catch ( WriterException e){
                    e.printStackTrace();

                }
            }
        });
    }
    public void toScan(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
