import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHTTPExemplo {
    public static void main(String[] args) throws IOException, InterruptedException {
        connectAndPrintBody("https://pt.wikipedia.org/wiki/Go_(linguagem_de_programa%C3%A7%C3%A3o)");
    }
	
    public static void connectAndPrintBody(String urlString) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                            .GET().uri(URI.create(urlString))
                            .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient
                    .send(request, HttpResponse
                                .BodyHandlers
                                .ofString());

        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.body());
    }
}