package edu.cnm.deepdive.imageretrievingdemo;

import android.app.Application;
import com.squareup.picasso.Picasso;

public class ImageRetrieverApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Picasso.setSingletonInstance(
        new Picasso.Builder(this)
            .loggingEnabled(true) // Disable for production
            .build()
    );
  }
}
