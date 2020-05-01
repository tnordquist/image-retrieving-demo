package edu.cnm.deepdive.imageretrievingdemo.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Animal {

  @Expose
  private String name;

  @Expose
  private Taxonomy taxonomy;

  @Expose
  private String location;

  @Expose
  private Speed speed;

  @Expose
  private String diet;

  private String lifespan;

  @Expose
  @SerializedName("image")
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Taxonomy getTaxonomy() {
    return taxonomy;
  }

  public void setTaxonomy(Taxonomy taxonomy) {
    this.taxonomy = taxonomy;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Speed getSpeed() {
    return speed;
  }

  public void setSpeed(Speed speed) {
    this.speed = speed;
  }

  public String getDiet() {
    return diet;
  }

  public void setDiet(String diet) {
    this.diet = diet;
  }

  public String getLifespan() {
    return lifespan;
  }

  public void setLifespan(String lifespan) {
    this.lifespan = lifespan;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
<<<<<<< HEAD
=======

  @NonNull
  @Override
  public String toString() {
    return name;
  }
}

class Taxonomy {

  @Expose
  String kingdom;

  @Expose
  String order;

  @Expose
  String family;
>>>>>>> 8262fdf49b81c7321ee5e0fd31d4b7237918aa11

  @NonNull
  @Override
  public String toString() {
    return name;
  }

}

<<<<<<< HEAD
=======



class Speed {

  @Expose
  private String metric;

  @Expose
  String imperial;

  public String getMetric() {
    return metric;
  }
>>>>>>> 8262fdf49b81c7321ee5e0fd31d4b7237918aa11


