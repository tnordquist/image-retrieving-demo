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
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.imageretrievingdemo.R;
import edu.cnm.deepdive.imageretrievingdemo.viewmodel.MainViewModel;
import java.util.Objects;

public class ImageFragment extends Fragment {

  private WebView contentView;
  private MainViewModel viewModel;
  String url;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_image, container, false);
    setupWebView(root);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    assert getParentFragment() != null;
    viewModel = new ViewModelProvider((Objects.requireNonNull(getActivity())))
        .get(MainViewModel.class);

    viewModel.getAnimals().observe(getViewLifecycleOwner(),
        (animals -> contentView.loadUrl(animals.get(24).getUrl())));
  }

  private void setupWebView(View root) {
    contentView = root.findViewById(R.id.content_view);
    contentView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
      }
    });
    WebSettings settings = contentView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(true);
    settings.setDisplayZoomControls(false);
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
  }

}

