import java.util.stream.Collectors;

public class StringLines {
    public static void main(String[] args) {
        var html = "<html>\n<body>\n<p>Teste<p>\n<body>\n<html>";

        var htmlLines = html.lines().collect(Collectors.toList());

        System.out.println(htmlLines);
        // [<html>, <body>, <p>Teste<p>, <body>, <html>]
    } 
}