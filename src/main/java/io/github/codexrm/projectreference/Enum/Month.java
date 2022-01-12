package io.github.codexrm.projectreference.Enum;

public enum Month {
  JAN ("January"),
  FEB("February"),
  MAR("March"),
  APR("April"),
  MAY("May"),
  JUN("June"),
  JUL("July"),
  AUG("August"),
  SEP("September"),
  OCT("October"),
  NOV("November"),
  DEC("December");
  private final String description;

  private Month(String description) {
      this.description = description;
  }

  @Override
  public String toString() {
      return description;
  }
}