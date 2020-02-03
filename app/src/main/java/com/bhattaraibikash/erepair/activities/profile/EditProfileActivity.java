package com.bhattaraibikash.erepair.activities.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.MainActivity;
import com.bhattaraibikash.erepair.api.UserApi;
import com.bhattaraibikash.erepair.responses.UserResponse;
import com.bhattaraibikash.erepair.strictmode.StrictModeClass;
import com.bhattaraibikash.erepair.url.Url;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private String imagePath;

    private ImageView ivProfileEdit;
    private TextView tvChoosePhoto;

    private EditText etNameEdit, etUsernameEdit, etEmailEdit, etPhoneEdit, etAddressEdit;
    private Button btnUpdate;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        checkPermission();

        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivProfileEdit = findViewById(R.id.ivProfileEdit);
        tvChoosePhoto = findViewById(R.id.tvChoosePhoto);

        etNameEdit = findViewById(R.id.etNameEdit);
        etUsernameEdit = findViewById(R.id.etUsernameEdit);
        etEmailEdit = findViewById(R.id.etEmailEdit);
        etPhoneEdit = findViewById(R.id.etPhoneEdit);
        etAddressEdit = findViewById(R.id.etAddressEdit);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle extra = getIntent().getExtras();

        if (!extra.isEmpty()) {
            etNameEdit.setText(extra.getString("name"));
            etUsernameEdit.setText(extra.getString("username"));
            etEmailEdit.setText(extra.getString("email"));
            etPhoneEdit.setText(extra.getString("phone"));
            etAddressEdit.setText(extra.getString("address"));
            Glide.with(EditProfileActivity.this)
                    .load(Url.base_url + extra.getString("imagePath"))
                    .placeholder(R.drawable.default_profile)
                    .into(ivProfileEdit);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        tvChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browseImage();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    updateProfile();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private void browseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select a photo", Toast.LENGTH_SHORT).show();
            }
        }

        Uri uri = data.getData();
        ivProfileEdit.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private boolean validate() {
        boolean status;
        if (etNameEdit.getText().toString().isEmpty()) {
            etNameEdit.setError("Name is Required");
            etNameEdit.requestFocus();
            status = false;
        } else if (etEmailEdit.getText().toString().isEmpty()) {
            etEmailEdit.setError("Email is Required");
            etEmailEdit.requestFocus();
            status = false;
        } else if (etAddressEdit.getText().toString().isEmpty()) {
            etAddressEdit.setError("Address is Required");
            etAddressEdit.requestFocus();
            status = false;
        } else if (etPhoneEdit.getText().toString().isEmpty()) {
            etPhoneEdit.setError("Phone is Required");
            etPhoneEdit.requestFocus();
            status = false;
        } else if (etUsernameEdit.getText().toString().isEmpty()) {
            etUsernameEdit.setError("Username is Required");
            etUsernameEdit.requestFocus();
            status = false;
        } else if (!etEmailEdit.getText().toString().trim().matches(emailPattern)) {
            etEmailEdit.setError("Invalid email");
            etEmailEdit.requestFocus();
            status = false;
        } else if (etUsernameEdit.getText().toString().length() < 6) {
            etUsernameEdit.setError("Username must be at least 6 character");
            etUsernameEdit.requestFocus();
            status = false;
        } else {
            status = true;
        }

        return status;
    }

    private void imageUpload() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",
                file.getName(), requestBody);

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<Void> call = userApi.uploadImage(Url.token, body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<Void> imageResponseResponse = call.execute();
            Toast.makeText(this, "Image inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void updateProfile() {
        String name = etNameEdit.getText().toString();
        String email = etEmailEdit.getText().toString();
        String username = etUsernameEdit.getText().toString();
        String phone = etPhoneEdit.getText().toString();
        String address = etAddressEdit.getText().toString();

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<UserResponse> responseCall = userApi.userProfileUpdate(Url.token, name, email, address, phone, username);

        responseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                    intent.putExtra("from", "EditProfile");
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
