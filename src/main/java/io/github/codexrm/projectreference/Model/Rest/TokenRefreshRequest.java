package io.github.codexrm.projectreference.model.Rest;

public class TokenRefreshRequest {

  private String refreshToken;

  public TokenRefreshRequest(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
