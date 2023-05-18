package io.github.codexrm.projectreference.model.model;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.codexrm.projectreference.model.dto.*;
import io.github.codexrm.projectreference.model.utils.JsonUtils;

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

    public ReferencePageDTO syncReferences(ReferenceLibraryDTO referenceLibraryDTO, Integer page, String token) {

        String  inputJson = JsonUtils.convertFromObjectToJson(referenceLibraryDTO);

        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Sync?page=" + page))
                .header("Content-Type","application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        ReferencePageDTO referencePageDTO = new ReferencePageDTO();

        try {
            referencePageDTO = JsonUtils.convertFromJsonToObject(response.get().body(), new TypeReference<ReferencePageDTO>() {
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return referencePageDTO;
    }

    public AuthenticationData userLogin(UserLogin userLogin) {

        String expiredDate = null;
        String  inputJson = JsonUtils.convertFromObjectToJson(userLogin);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signin"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
      

       UserDTO userDTO = new UserDTO();


        try {
            userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), new TypeReference<UserDTO>() {
            });

            expiredDate = response.get().headers().firstValue("Date").get();


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        response.join();
        String token = userDTO.getTokenType() + " " + userDTO.getAccessToken();

        return new AuthenticationData( userDTO.getId(),  userDTO.getUsername(),  userDTO.getName(),  userDTO.getLastName(),  userDTO.getEmail(),  userDTO.isEnabled(), token, expiredDate);
    }

}
