package edu.cnm.deepdive.imageretrievingdemo.view;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.imageretrievingdemo.BuildConfig;
import edu.cnm.deepdive.imageretrievingdemo.R;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.model.service.AnimalService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageFragment extends Fragment {

  private WebView contentView;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_image, container, false);
    setupWebView(root);
    return root;
  }

  private void setupWebView(View root) {
    contentView = root.findViewById(R.id.content_view);
    contentView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        // TODO Update view to indicate that load is complete.
      }
    });
    WebSettings settings = contentView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(true);
    settings.setDisplayZoomControls(false);
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
    new Retriever().start();
  }

  /**
   * The java Thread class will be replaced by AsyncTask because AsyncTask enables proper and easy
   * use of the UI thread. This class allows performing background operations and publishing results
   * on the UI thread without having to manipulate threads and/or handlers. An asynchronous task is
   * defined by a computation that runs on a background thread and whose result is published on the
   * UI thread.
   */
  private class Retriever extends Thread {

    @Override
    public void run() {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      AnimalService service = retrofit.create(AnimalService.class);
      try {
        Response<List<Animal>> response = service.getAnimals(BuildConfig.CLIENT_KEY).execute();
        if (response.isSuccessful()) {
          List<Animal> animals = response.body();
          String url = animals.get(48).getUrl();
          getActivity().runOnUiThread(() -> contentView.loadUrl(url));
        } else {
          Log.e("AnimalService", response.message());
        }
      } catch (IOException e) {
        Log.e("AnimalService", e.getMessage(), e);
      }
    }

  }


}
