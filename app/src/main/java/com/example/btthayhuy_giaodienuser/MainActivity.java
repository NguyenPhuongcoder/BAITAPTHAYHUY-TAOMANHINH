package com.example.btthayhuy_giaodienuser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnLogin = findViewById(R.id.button);
        EditText edtEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText edtPassword = findViewById(R.id.editTextTextPassword);
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString();
            // Thông tin mặc định
            String defaultEmail = "user@gmail.com";
            String defaultPassword = "123456";
            if(email.equals(defaultEmail) && password.equals(defaultPassword)) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}