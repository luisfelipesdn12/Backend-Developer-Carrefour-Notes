import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class Inference {
    public static void main(String[] args) throws IOException {
        ///connectAndPrintBody("https://pt.wikipedia.org/wiki/Go_(linguagem_de_programa%C3%A7%C3%A3o)");

        var hello;
        hello = "hello";
        System.out.println(hello);
    }
	
    // Não podemos utilizar connectAndPrintBody(var urlString)
    public static void connectAndPrintBody(String urlString) throws IOException {
        // mas podemos usar no escopo local do método:
        var url = new URL(urlString);
        var urlConnection = url.openConnection();
        var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        System.out.println(
            bufferedReader.lines()
                        .collect(Collectors.joining())
                        .replaceAll(">", ">\n")
        );
    }
}