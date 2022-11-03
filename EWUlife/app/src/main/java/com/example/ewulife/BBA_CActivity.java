package com.example.ewulife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class BBA_CActivity extends AppCompatActivity {
    WebView webView;
    String load_url;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_a__c);
        webView=findViewById(R.id.web_bba_c);


        fstore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fstore.collection("course_list").document("ZK5EYrNbRA37mRcWs4so");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                load_url = documentSnapshot.getString("bba");

                if(load_url != null){
                    Toast.makeText(BBA_CActivity.this, "DataBase updated", Toast.LENGTH_SHORT).show();
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl(load_url);
                }
                else{
                    Toast.makeText(BBA_CActivity.this, "DataBase in Maintainace", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(BBA_CActivity.this,CourseChartActivity.class);
                    startActivity(intent);

                }

            }
        });

    }
}