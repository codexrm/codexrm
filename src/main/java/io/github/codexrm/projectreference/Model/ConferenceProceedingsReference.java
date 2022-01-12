package io.github.codexrm.projectreference.Model;

public class ConferenceProceedingsReference extends Reference {
  
  private String volume;
  private String serie;
  private String address;

  public ConferenceProceedingsReference() {
    super();
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getSerie() {
    return serie;
  }

  public void setSerie(String serie) {
    this.serie = serie;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}