package io.github.codexrm.projectreference.model.Rest;

import io.github.codexrm.projectreference.model.dto.*;
import io.github.codexrm.projectreference.model.utils.AlertMessage;
import io.github.codexrm.projectreference.model.utils.JsonUtils;
import javafx.scene.control.Alert;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String ReferenceURL = "http://localhost:8081/api/Reference/";
    private static final String authURL = "http://localhost:8081/api/auth/";
    private AlertMessage alert = new AlertMessage();

    //Reference
    public ReferencePageDTO syncReferences(ReferenceLibraryDTO referenceLibraryDTO, Integer page, String token) {

        String  inputJson = JsonUtils.convertFromObjectToJson(referenceLibraryDTO);

        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Sync?page=" + page)).header("Content-Type","application/json").header("Authorization", token).
                POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        ReferencePageDTO referencePageDTO = new ReferencePageDTO();

        try {
            referencePageDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferencePageDTO.class);
        } catch (InterruptedException | ExecutionException e) {
            alert.getAlert(null, Alert.AlertType.ERROR, "Error","", "Hubo un error en el servidor. Intentelo mas tarde" );
        }
        response.join();
        return referencePageDTO;
    }

    //User
    public AuthenticationData userLogin(UserLogin userLogin) {

        String  inputJson = JsonUtils.convertFromObjectToJson(userLogin);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signin")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

       UserDTO userDTO = new UserDTO();

        try {
            if (response.get().statusCode() == 401) {
                response.join();
                return null;
            } else {
                userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserDTO.class);
                response.join();
            }

        } catch (InterruptedException | ExecutionException e) {
            alert.getAlert(null, Alert.AlertType.ERROR, "Error","", "Hubo un error en el servidor. Intentelo mas tarde" );
        }
        response.join();
        String token = userDTO.getTokenType() + " " + userDTO.getAccessToken();

        return new AuthenticationData( userDTO.getId(),  userDTO.getUsername(),  userDTO.getName(),  userDTO.getLastName(),  userDTO.getEmail(), userDTO.isEnabled(), token, userDTO.getRefreshToken(),
                userDTO.getTokenExpirationDate(), userDTO.getRefreshTokenExpirationDate());
    }


    public boolean userLogout(String token) throws ExecutionException, InterruptedException {

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signout")).header("Content-Type","application/json").header("Authorization", token).
                POST(HttpRequest.BodyPublishers.noBody()).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        return response.get().statusCode() == 200;
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshToken) {

        String  inputJson = JsonUtils.convertFromObjectToJson(refreshToken);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "refreshtoken")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();

        try {
            tokenRefreshResponse =  JsonUtils.convertFromJsonToObject(response.get().body(), TokenRefreshResponse.class);
        } catch (InterruptedException | ExecutionException e) {
            alert.getAlert(null, Alert.AlertType.ERROR, "Error","", "Hubo un error en el servidor. Intentelo mas tarde" );
        }

        response.join();
        return tokenRefreshResponse;
    }
}
