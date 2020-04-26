package edu.cnm.deepdive.imageretrievingdemo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.imageretrievingdemo.R;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.viewmodel.MainViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageFragment extends Fragment {

  private WebView contentView;
  private ViewModel viewModel;
  String url;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_image, container, false);
//    viewModel = new ViewModelProvider((getActivity()))
//        .get(MainViewModel.class);
    setupWebView(root);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    assert getParentFragment() != null;
    viewModel = new ViewModelProvider((Objects.requireNonNull(getActivity())))
        .get(MainViewModel.class);

//    final Observer<List<Animal>> resultObserver = animals -> {
//      url = animals.get(24).getUrl();
//      contentView.loadUrl(url);
//    };
//   viewModel.getClass().observe(getViewLifecycleOwner(),resultObserver);
  }

  private void setupWebView(View root) {
    contentView = root.findViewById(R.id.content_view);
    contentView.setWebViewClient(new WebViewClient() {

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
    contentView.loadUrl(url);
  }

  /**
   * AsyncTask enables proper and easy * use of the UI thread. This class allows performing
   * background operations and publishing results * on the UI thread without having to manipulate
   * threads and/or handlers. An asynchronous task is * defined by a computation that runs on a
   * background thread and whose result is published on the * UI thread.
   */
/*  private class RetrieveImageTask extends AsyncTask<List<Animal>, Void, List<Animal>> {

    AnimalService service;

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      service = retrofit.create(AnimalService.class);
    }

    @Override
    protected List<Animal> doInBackground(List<Animal>... lists) {

      List<Animal> animals = null;
      try {
        Response<List<Animal>> response = service.getAnimals(BuildConfig.CLIENT_KEY).execute();
        if (response.isSuccessful()) {
          animals = response.body();
        }
      } catch (IOException e) {
        Log.e("AnimalService", e.getMessage(), e);
      }
      return animals;

    }

    @Override
    protected void onPostExecute(List<Animal> animals) {
      super.onPostExecute(animals);
      String url = animals.get(24).getUrl();
      contentView.loadUrl(url);
    }
  }*/
}

