package io.github.codexrm.projectreference.model.model;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.codexrm.projectreference.model.dto.*;
import io.github.codexrm.projectreference.model.utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String ReferenceURL = "http://localhost:8081/api/Reference/";
    private static final String authURL = "http://localhost:8081/api/auth/";

    public List<ReferenceDTO> syncReferences(ReferenceLibraryDTO libraryDTO) {

        String  inputJson = JsonUtils.convertFromObjectToJson(libraryDTO);

        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "syncTable"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        List<ReferenceDTO> referenceList = new ArrayList<>();

        try {
            referenceList = JsonUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<ReferenceDTO>>() {
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return referenceList;
    }

    public User userLogin(UserLogin userLogin) {

        String  inputJson = JsonUtils.convertFromObjectToJson(userLogin);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signin"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
      

       UserDTO userDTO = new UserDTO();


        try {
            userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), new TypeReference<UserDTO>() {
            });

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        response.join();
        String token = userDTO.getTokenType() + " " + userDTO.getAccessToken();
        return new User( userDTO.getId(),  userDTO.getUsername(),  userDTO.getName(),  userDTO.getLastName(),  userDTO.getEmail(),  userDTO.isEnabled(),  token);
    }

}
