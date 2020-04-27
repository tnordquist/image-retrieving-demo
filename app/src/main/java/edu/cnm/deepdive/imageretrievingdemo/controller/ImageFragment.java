package edu.cnm.deepdive.imageretrievingdemo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.imageretrievingdemo.R;
import edu.cnm.deepdive.imageretrievingdemo.viewmodel.MainViewModel;
import java.util.Objects;

public class ImageFragment extends Fragment implements OnItemSelectedListener {

  private WebView contentView;
  private MainViewModel viewModel;

  private Spinner spinner;
  private SpinnerAdapter spinnerAdapter;
  private AdapterView.OnItemSelectedListener itemSelected;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_image, container, false);
    setupWebView(root);
    spinner = root.findViewById(R.id.animals_spinner);
    spinner.setOnItemSelectedListener(this);

    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        Objects.requireNonNull(getContext()),
        R.array.animals_array, R.layout.support_simple_spinner_dropdown_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    assert getParentFragment() != null;
    viewModel = new ViewModelProvider((Objects.requireNonNull(getActivity())))
        .get(MainViewModel.class);
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
    settings.setJavaScriptEnabled(false);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(true);
    settings.setDisplayZoomControls(false);
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
    viewModel.getAnimals().observe(getViewLifecycleOwner(),
        (animals -> contentView.loadUrl(animals.get(pos).getUrl())));

  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {
    viewModel.getAnimals().observe(getViewLifecycleOwner(),
        (animals -> contentView.loadUrl(animals.get(0).getUrl())));
  }
}

