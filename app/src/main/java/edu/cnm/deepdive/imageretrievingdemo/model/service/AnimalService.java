package edu.cnm.deepdive.imageretrievingdemo.model.service;

import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.model.ApiKey;
import io.reactivex.Single;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AnimalService {

  @GET("getKey")
  public Call <ApiKey> getApiKey();

  @FormUrlEncoded
  @POST("getAnimals")
  public Call<List<Animal>> getAnimals(@Field("key") String key);

  //If the application passed the url dynamically
  @GET
  public Object getObjects(@Url String url);

}
