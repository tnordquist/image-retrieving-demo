package edu.cnm.deepdive.imageretrievingdemo.model.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.imageretrievingdemo.BuildConfig;
import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import java.util.List;
import java.util.logging.Level;

public interface AnimalService {


  static AnimalService getInstance() {
    return InstanceHolder.INSTANCE;
  }

//  @GET("getKey")
//  public Single <ApiKey> getApiKey();

  @FormUrlEncoded
  @POST("getAnimals")
  Single<List<Animal>> getAnimals(@Field("key") String key);

  class InstanceHolder {

    private static final AnimalService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
              .excludeFieldsWithoutExposeAnnotation()
              .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
              .addInterceptor(interceptor)
              .build();
      Retrofit retrofit = new Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create(gson))
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .client(client)
              .baseUrl(BuildConfig.BASE_URL)
              .build();
      INSTANCE = retrofit.create(AnimalService.class);
    }

  }
}
