package edu.cnm.deepdive.imageretrievingdemo;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.imageretrievingdemo.service.ApodService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
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

  private class Retriever extends Thread {

    @Override
    public void run() {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .setDateFormat("yyyy-MM-dd")
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      AnimalService service = retrofit.create(AnimalService.class);
      try {
        Response<Animal> response = service.get(BuildConfig.API_KEY, "2019-01-27").execute();
        if (response.isSuccessful()) {
          Animal animal = response.body();
          String url = animal.getUrl();
          getActivity().runOnUiThread(() -> contentView.loadUrl(url));
        } else {
          Log.e("ApodService", response.message());
        }
      } catch (IOException e) {
        Log.e("ApodService", e.getMessage(), e);
      }
    }

  }


}
