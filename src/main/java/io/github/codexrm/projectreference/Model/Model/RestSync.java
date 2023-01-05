package io.github.codexrm.projectreference.model.model;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.codexrm.projectreference.model.dto.ReferenceDTO;
import io.github.codexrm.projectreference.model.dto.ReferenceLibraryDTO;
import io.github.codexrm.projectreference.model.utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestSync {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL = "http://localhost:8081/Sync/";

    public List<ReferenceDTO> syncReferences(ReferenceLibraryDTO libraryDTO) {

        String  inputJson = JsonUtils.convertFromObjectToJson(libraryDTO);

        HttpRequest req = HttpRequest.newBuilder(URI.create(URL + "syncTable"))
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
}
