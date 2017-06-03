package com.example.qrscan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button scan_qr;
    EditText qrscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan_qr = (Button) findViewById(R.id.qr_scan);
        final Activity activity = this;
        qrscan = (EditText) findViewById(R.id.showqr);
        scan_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intergrator = new IntentIntegrator(activity);
                intergrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intergrator.setPrompt("Scan");
                intergrator.setCameraId(0);
                intergrator.setBeepEnabled(false);
                intergrator.setBarcodeImageEnabled(false);
                intergrator.setCaptureActivity(CaptureActivityPortrait.class);
                intergrator.initiateScan();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                qrscan.setText(result.getContents());
            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void toGen(View view){
        startActivity(new Intent(this,Generator.class));
    }
}