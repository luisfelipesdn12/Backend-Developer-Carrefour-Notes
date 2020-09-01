package luisfelipesdn12.dio.telegramrequests.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import luisfelipesdn12.dio.telegramrequests.dto.ResultBotTelegramList;

@Component
public class JavaHttpClient {
    @Value("${telegram.api}")
    private String BASE_PATH;
    @Value("${telegram.token}")
    private String TOKEN;

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public ResultBotTelegramList buscarAtualizacao() {
        ResultBotTelegramList resultBotTelegramList = null;
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_PATH + TOKEN + "/getUpdates"))
                    .GET()
                    .build();

        HttpResponse<String> stringHttpResponse = sendRequest(request);

        try {
            resultBotTelegramList = objectMapper
                            .readValue(stringHttpResponse.body(), ResultBotTelegramList.class)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBotTelegramList;
    }

    public 
    
    private HttpResponse<String> sendRequest(HttpRequest request) {
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}