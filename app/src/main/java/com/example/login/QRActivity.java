package com.example.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CameraPreview;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoderFactory;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import android.Manifest;

import java.util.Arrays;
import java.util.List;

public class QRActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private DecoratedBarcodeView barcodeScannerView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);

        barcodeScannerView = findViewById(R.id.barcode_scanner);
        resultTextView = findViewById(R.id.resultTextView);

        // Check if we have camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            startScanning();  // Start scanning if permission is granted
        }
    }

    // Start scanning QR codes
    private void startScanning() {
        // Create a list of formats to scan (QR Code only in this case)
        List<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE);

        // Set the decoder factory to use the list of formats
        DecoderFactory decoderFactory = new DefaultDecoderFactory(formats);
        barcodeScannerView.setDecoderFactory(decoderFactory);

        // Set up a barcode callback for decoding QR codes
        barcodeScannerView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                // Handle the result when a QR code is detected
                if (result != null) {
                    String scannedData = result.getText();  // Get the scanned text
                    resultTextView.setText(scannedData);   // Set the result in the TextView
                }
            }

            @Override
            public void possibleResultPoints(java.util.List<com.google.zxing.ResultPoint> resultPoints) {
                // Handle result points (you can use them to show scanning progress if needed)
            }
        });

        barcodeScannerView.resume(); // Start the camera preview
    }

    // Handle camera permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();  // Start the scanner if permission is granted
            } else {
                Toast.makeText(this, "Camera permission is required to scan QR codes.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Stop the camera preview when the activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        if (barcodeScannerView != null) {
            barcodeScannerView.pause(); // Pause the camera when the activity is paused
        }
    }

    // Restart the camera preview when the activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        if (barcodeScannerView != null) {
            barcodeScannerView.resume(); // Resume the camera when the activity is resumed
        }
    }

    // Stop the camera preview when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (barcodeScannerView != null) {
            barcodeScannerView.pause(); // Stop the camera when the activity is destroyed
        }
    }
}
