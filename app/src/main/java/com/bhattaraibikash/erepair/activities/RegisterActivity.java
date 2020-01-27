package com.bhattaraibikash.erepair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.models.User;
import com.bhattaraibikash.erepair.responses.UserResponse;
import com.bhattaraibikash.erepair.strictmode.StrictModeClass;
import com.bhattaraibikash.erepair.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView loginLink;
    private EditText etName, etEmail, etAddress, etPhone, etUsernameReg, etPasswordReg;
    private Button btnSignUp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etUsernameReg = findViewById(R.id.etUsernameReg);
        etPasswordReg = findViewById(R.id.etPasswordReg);
        btnSignUp = findViewById(R.id.btnSighUp);

        loginLink = findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    signUp();
                }
            }
        });
    }

    private boolean validate() {
        boolean status;
        if (etName.getText().toString().isEmpty()) {
            etName.setError("Name is Required");
            etName.requestFocus();
            status = false;
        } else if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Email is Required");
            etEmail.requestFocus();
            status = false;
        } else if (etAddress.getText().toString().isEmpty()) {
            etAddress.setError("Address is Required");
            etAddress.requestFocus();
            status = false;
        } else if (etPhone.getText().toString().isEmpty()) {
            etPhone.setError("Phone is Required");
            etPhone.requestFocus();
            status = false;
        } else if (etUsernameReg.getText().toString().isEmpty()) {
            etUsernameReg.setError("Username is Required");
            etUsernameReg.requestFocus();
            status = false;
        } else if (etPasswordReg.getText().toString().isEmpty()) {
            etPasswordReg.setError("Password is Required");
            etPasswordReg.requestFocus();
            status = false;
        } else if (!etEmail.getText().toString().trim().matches(emailPattern)) {
            etEmail.setError("Invalid email");
            etEmail.requestFocus();
            status = false;
        } else if (etUsernameReg.getText().toString().length() < 6) {
            etUsernameReg.setError("Username must be at least 6 character");
            etUsernameReg.requestFocus();
            status = false;
        } else if (etPasswordReg.getText().toString().length() < 6) {
            etPasswordReg.setError("Password must be at least 6 character");
            etPasswordReg.requestFocus();
            status = false;
        } else {
            status = true;
        }

        return status;
    }

    private void signUp() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddress.getText().toString();
        String phone = etPhone.getText().toString();
        String username = etUsernameReg.getText().toString();
        String password = etPasswordReg.getText().toString();

        User user = new User(name, email, address, phone, username, password);

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> userResponseCall = userApi.userRegister(user);

        //Synchronous method
        StrictModeClass.StrictMode();
        try {
            Response<UserResponse> userResponse = userResponseCall.execute();
            Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
