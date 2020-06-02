package edu.cnm.deepdive.imageretrievingdemo.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.imageretrievingdemo.R;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new Picasso.Builder(this)
        .loggingEnabled(true) // Disable for production
        .build();
    setContentView(R.layout.activity_main);
  }


}
