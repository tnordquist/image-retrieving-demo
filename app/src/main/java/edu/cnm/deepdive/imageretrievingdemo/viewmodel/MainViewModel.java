package edu.cnm.deepdive.imageretrievingdemo.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.imageretrievingdemo.BuildConfig;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.model.service.AnimalService;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Animal>> animals;
    private MutableLiveData<Throwable> throwable;
    AnimalService animalService;

    public MainViewModel(@NonNull Application application){
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

