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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.imageretrievingdemo.R;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.viewmodel.MainViewModel;
import java.util.List;
import java.util.Objects;

public class ImageFragment extends Fragment implements OnItemSelectedListener {

  ImageView imageView;
  private MainViewModel viewModel;
  Toolbar toolbar;
  private Spinner spinner;
  private List<Animal> animals;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_image, container, false);
    imageView = root.findViewById(R.id.image_view);
    toolbar = root.findViewById(R.id.toolbar);
    spinner = root.findViewById(R.id.animals_spinner);
    spinner.setOnItemSelectedListener(this);
    toolbar.setTitle(R.string.app_name);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    assert getParentFragment() != null;
    viewModel = new ViewModelProvider((Objects.requireNonNull(getActivity())))
        .get(MainViewModel.class);
    viewModel.getAnimals().observe(getViewLifecycleOwner(), (animals) -> {
      this.animals = animals;
      ArrayAdapter<Animal> adapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_item,
          animals);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);
    });
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
    Picasso.get().load(animals.get(pos).getUrl()).into(imageView);
  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {

  }
}

