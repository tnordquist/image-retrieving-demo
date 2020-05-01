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

import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Animal>> animals;
    private MutableLiveData<Throwable> throwable;
    AnimalService animalService;

    public MainViewModel(@NonNull Application application) {
        super(application);
        animalService = AnimalService.getInstance();
        animals = new MutableLiveData<>();
        throwable = new MutableLiveData<>();
        loadAnimals();
    }

    public LiveData<List<Animal>> getAnimals() {
        return animals;
    }

    public MutableLiveData<Throwable> getThrowable() {
        return throwable;
    }

    @SuppressLint("CheckResult")
    public void loadAnimals() {

        animalService.getAnimals(BuildConfig.CLIENT_KEY)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        (animals1 -> this.animals.postValue(animals1)),
                        throwable1 -> {
                            this.throwable.postValue(throwable1);
                        }
                );


    }
}

