package io.github.codexrm.projectreference.Model;

public class BookLetReference extends Reference {

  private String howpublished;
  private String address;

  public BookLetReference() {
    super();
  }

  public String getHowpublished() {
    return howpublished;
  }

  public void setHowpublished(String howpublished) {
    this.howpublished = howpublished;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}