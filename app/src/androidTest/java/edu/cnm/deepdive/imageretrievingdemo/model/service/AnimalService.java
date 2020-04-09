package edu.cnm.deepdive.imageretrievingdemo.model.service;

import edu.cnm.deepdive.imageretrievingdemo.model.Animal;
import edu.cnm.deepdive.imageretrievingdemo.model.ApiKey;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AnimalService {

  @GET("getKey")
  public Single<ApiKey> getApiKey();

  @FormUrlEncoded
  @POST("getAnimals")
  public Single<List<Animal>> getAnimals(@Field("key") String key);

}
