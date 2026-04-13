package br.edu.fatecpg.tecprog.repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeApi {

    public static String buscarempresa(String cnpj) {
        if(cnpj == null || cnpj.trim().isEmpty()) {
            return "Cnpj nulo ou vazio!!!! Vacilão!";
        } else {
            try {
                String uri = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder().uri(URI.create(uri)).build();
                HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                if(resp.body().contains("bad_request") || resp.body().contains("inválido")) {
                    return "Cnpj inválido!";
                }
                return resp.body();
            }catch(Exception e) {
                System.err.println("Exceção capturada: " + e.getMessage());
                return "Erro!";
            }
        }
    }
}
