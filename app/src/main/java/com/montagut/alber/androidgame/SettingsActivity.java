package com.montagut.alber.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class SettingsActivity extends AppCompatActivity {

    EditText userName;
    ImageView userImage;
    ImageButton imageButton;
    ImageButton galleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        userName = (EditText) findViewById(R.id.userName);
        userImage = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        galleryButton = (ImageButton) findViewById(R.id.galleryButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 2);
                }
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Seleccione una imagen"),
                        1);
            }
        });
    }

    @Override
    protected void onPause() {
        saveData(userName.getText().toString());
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("username", "Nom usuari");
        userName.setText(name);
    }

    private void saveData(String text){
        // Desem dades
        SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("username", text);
        ed.apply();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                        userImage.setImageBitmap(bmp);
                    }
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    userImage.setImageBitmap(imageBitmap);
                }
                break;
        }
    }

}