package com.bhattaraibikash.erepair.activities.info;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.bll.RequestBLL;
import com.bhattaraibikash.erepair.models.Request;
import com.bhattaraibikash.erepair.notification.CreateChannel;
import com.bhattaraibikash.erepair.strictmode.StrictModeClass;

public class ProfessionalActivity extends AppCompatActivity {
    private EditText etNamePro, etEmailPro, etAddressPro, etPhonePro, etSkillPro;
    private Button btnRequest;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private NotificationManagerCompat notificationManagerCompat;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        getSupportActionBar().setTitle("Become Professional");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        etNamePro = findViewById(R.id.etNamePro);
        etEmailPro = findViewById(R.id.etEmailPro);
        etAddressPro = findViewById(R.id.etAddressPro);
        etPhonePro = findViewById(R.id.etPhonePro);
        etSkillPro = findViewById(R.id.etSkillPro);
        btnRequest = findViewById(R.id.btnRequest);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendRequest();
                }
            }
        });
    }

    private boolean validate() {
        boolean status;
        if (etNamePro.getText().toString().isEmpty()) {
            etNamePro.setError("Name is Required");
            etNamePro.requestFocus();
            status = false;
        } else if (etEmailPro.getText().toString().isEmpty()) {
            etEmailPro.setError("Email is Required");
            etEmailPro.requestFocus();
            status = false;
        } else if (etAddressPro.getText().toString().isEmpty()) {
            etAddressPro.setError("Address is Required");
            etAddressPro.requestFocus();
            status = false;
        } else if (etPhonePro.getText().toString().isEmpty()) {
            etPhonePro.setError("Phone is Required");
            etPhonePro.requestFocus();
            status = false;
        }else if (etSkillPro.getText().toString().isEmpty()) {
            etSkillPro.setError("Skills is Required");
            etSkillPro.requestFocus();
            status = false;
        } else if (!etEmailPro.getText().toString().trim().matches(emailPattern)) {
            etEmailPro.setError("Invalid email");
            etEmailPro.requestFocus();
            status = false;
        }else {
            status = true;
        }

        return status;
    }

    private void sendRequest() {
        String name = etNamePro.getText().toString();
        String email = etEmailPro.getText().toString();
        String address = etAddressPro.getText().toString();
        String phone = etPhonePro.getText().toString();
        String skills = etSkillPro.getText().toString();

        Request request = new Request(name, email, address, phone, skills);
        RequestBLL requestBLL = new RequestBLL();

        StrictModeClass.StrictMode();
        if (requestBLL.sendRequest(request)) {
            Toast.makeText(this, "Request has been send Successful.", Toast.LENGTH_SHORT).show();

            count++;
            ProfessionalRequestNotification("Professional Request", "Your request to become a professional has been send.");
            finish();
        } else {
            Toast.makeText(this, "Failed.", Toast.LENGTH_SHORT).show();

        }
    }

    private  void ProfessionalRequestNotification(String title, String message) {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setContentText(message)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(count, notification);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
