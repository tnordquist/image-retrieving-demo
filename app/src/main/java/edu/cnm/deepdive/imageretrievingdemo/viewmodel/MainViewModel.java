package edu.cnm.deepdive.imageretrievingdemo.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.imageretrievingdemo.BuildConfig;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.model.service.AnimalService;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {

  AnimalService service;
  private MutableLiveData<List<Animal>> animals;

  public MainViewModel(@NonNull Application application) {
    super(application);
    loadImage();
    animals = new MutableLiveData<>();
  }

  public LiveData<List<Animal>> getAnimals() {
    return animals;
  }

  //  public void setAnimals(
  //      LiveData<List<Animal>> animals) {
  //    new RetrieveImageTask().execute();
  //  }

  //  @SuppressLint("StaticFieldLeak")
  //  public void loadImage() {

  /**
   * AsyncTask enables proper and easy * use of the UI thread. This class allows performing
   * background operations and publishing results * on the UI thread without having to manipulate
   * threads and/or handlers. An asynchronous task is * defined by a computation that runs on a
   * background thread and whose result is published on the * UI thread.
   */
  private void loadImage() {
    new AsyncTask<List<Animal>, Void, List<Animal>>() {

      AnimalService service;
      List<Animal> animals;

      @Override
      protected void onPreExecute() {
        super.onPreExecute();
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        Retrofit retrofit = new Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        service = retrofit.create(AnimalService.class);
      }

      @Override
      protected final List<Animal> doInBackground(List<Animal>... lists) {

        try {
          Response<List<Animal>> response = service.getAnimals(BuildConfig.CLIENT_KEY).execute();
          if (response.isSuccessful()) {
            animals = response.body();
            MainViewModel.this.animals.postValue(animals);

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
      }
    }.execute();
  }

}

