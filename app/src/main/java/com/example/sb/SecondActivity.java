package com.example.sb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private String Greeting = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_second);
    }

   public void onClickSendMessage(View view) {
        Bundle extras = getIntent().getExtras();
        String name = "";
        if (extras != null) {
            EditText nameText = findViewById(R.id.name);
            name = nameText.getText().toString();
            Greeting = extras.getString(MainActivity.GREETING) + name;
        }

        // check if user write name
       if (name.isEmpty()) {
            resetGreeting();
       }

       // move to main activity
        Intent intent = new Intent();
        intent.putExtra(MainActivity.GREETING_RESULT, Greeting);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void resetGreeting() {Greeting = "";}
}