package com.example.sb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    static final String GREETING = "GREETING";
    static final String GREETING_VALUE = "Hello, my friend - ";
    static final String GREETING_RESULT = "GREETING_RESULT";
    static final String GREETING_NOT_SET = "Name wasn't set :(";

    // create callback for getting result from second activity
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // get text view
                TextView textView = findViewById(R.id.resultGreeting);
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        String callBackGreeting = intent.getStringExtra(GREETING_RESULT);
                        if (!Objects.equals(callBackGreeting, "")) {
                            // update view with user name
                            textView.setText(callBackGreeting);
                            return;
                        }
                        // if user not set name
                        textView.setText(GREETING_NOT_SET);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        // if user decides to close second activity
        super.onPause();
        TextView textView = findViewById(R.id.resultGreeting);
        textView.setText("");
    }

   public void onClick(View view) {
    Intent intent = new Intent(this, SecondActivity.class);
    // variables for second activity
    intent.putExtra(GREETING, GREETING_VALUE);
    mStartForResult.launch(intent);
   }
}