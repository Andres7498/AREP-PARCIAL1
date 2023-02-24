package org.example;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {break; }
        }
        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>CHATGPT</h1>\n" +
                "<form action=\"/hello\">\n" +
                "    <label for=\"name\">Escribe aca:</label><br>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" value=\"Hola\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "                loadPostMSg(name) \n"+
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "<h1>Respuesta</h1>\n" +
                "<form action=\"/hellopost\">\n" +
                "    <label for=\"postname\">Respuesta:</label><br>\n" +
                "    <input type=\"text\" id=\"postname\" name=\"name\" value=\"Debe cambiar\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(name)\">\n" +
                "</form>\n" +
                "\n" +
                "<div id=\"postrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "            function loadPostMsg(name){\n" +
                "                let url = \"/hellopost?name=\" + name.value;\n" +
                "\n" +
                "                fetch (url, {method: 'POST'})\n" +
                "                    .then(x => x.text())\n" +
                "                    .then(y => name;\n" +
                "            }\n" +
                "        </script>"+
                "<div id=\"getrespmsg\"></div>"
                + "</body>\n"
                + "</html>\n";
        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}