package com.bhattaraibikash.erepair.activities.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText etOldPassword, etNewPassword;
    private Button btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    changePassword();
                }
            }
        });
    }

    private boolean validate() {
        boolean status;
        if (etOldPassword.getText().toString().isEmpty()) {
            etOldPassword.setError("Old password is Required");
            etOldPassword.requestFocus();
            status = false;
        } else if (etNewPassword.getText().toString().isEmpty()) {
            etNewPassword.setError("New Password is Required");
            etNewPassword.requestFocus();
            status = false;
        } else if (etNewPassword.getText().toString().length() < 6) {
            etNewPassword.setError("Password must be at least 6 character");
            etNewPassword.requestFocus();
            status = false;
        } else {
            status = true;
        }

        return status;
    }

    private void changePassword() {
        String oldpassword = etOldPassword.getText().toString();
        String newpassword = etNewPassword.getText().toString();

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<Void> responseCall = userApi.changePassword(Url.token, oldpassword, newpassword);

        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChangePasswordActivity.this, "Password has been changed Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Your old password does not match", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
