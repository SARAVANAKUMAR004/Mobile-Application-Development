 package com.example.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

 TextToSpeech textToSpeech;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
         @Override
         public void onInit(int status) {
             if (status == TextToSpeech.SUCCESS) {
                 textToSpeech.setLanguage(Locale.US);
             }
         }
     });

     Button speakButton = findViewById(R.id.speakButton);
     speakButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String text = "Hello, this is a text to speech example!";
             textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
         }
     });
 }

 @Override
 protected void onDestroy() {
     if (textToSpeech != null) {
         textToSpeech.stop();
         textToSpeech.shutdown();
     }
     super.onDestroy();
 }
