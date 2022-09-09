package com.example.buihuuthanh_dawd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName, edEmail, edDetail;
    Spinner spGripe;
    Button btnSend;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edDetail = findViewById(R.id.edDetail);

        spGripe = findViewById(R.id.spGripe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGripe.setAdapter(adapter);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSave();

            }
        });
    }
    private void onSave(){
        if (!validate()){
            return;
        }
        Feedback feedback = new Feedback();
        feedback.name = edName.getText().toString();
        feedback.email = edEmail.getText().toString();
        feedback.detail = edDetail.getText().toString();
        feedback.gripe = spGripe.toString();
        long id = db.feedbackDao().insertFeedback(feedback);
        if (id > 0) {
            String mes = String.valueOf(db.feedbackDao().getAllFeedback().size());
            Toast.makeText(MainActivity.this, "so ban ghi: "+mes, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập username";
        }else if (edEmail.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập email";
        }
        else if (edDetail.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập feedback";
        }
        if (mes != null) {
            Toast.makeText(MainActivity.this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}