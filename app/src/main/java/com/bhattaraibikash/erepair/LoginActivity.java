package com.bhattaraibikash.erepair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.isEmpty()){
                    etUsername.setError("Enter Username!");
                } else if(password.isEmpty()){
                    etPassword.setError("Enter Password!");
                } else {
                    verify(etUsername.getText().toString(), etPassword.getText().toString());
                }
            }
        });
    }

    private void verify (String username, String password){
        if((username.equals("admin")) && (password.equals("admin"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            etUsername.setError("Incorrect Username or password");
        }
    }
}
