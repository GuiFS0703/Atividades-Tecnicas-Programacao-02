package com.goldenCompass.countryExplorer.service;

import com.goldenCompass.countryExplorer.model.Pais;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service //configs básicas do service
public class PaisService {

    public static String url = "https://restcountries.com/v3.1/name/";
    public static ObjectMapper mapper = new ObjectMapper(); //converter p objeto java
    public static RestTemplate rest = new RestTemplate(); //igual ao httpclient

    public static Pais buscarPais(String nome) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome inválido!");
            }

            String json = rest.getForObject(url + nome, String.class);
            Pais[] paises = mapper.readValue(json, Pais[].class);

            JsonNode raiz = mapper.readTree(json);

            JsonNode primeiro = raiz.get(0);
            String urlBandeira = primeiro.path("flags").path("png").asText("");


            paises[0].setUrlBandeira(urlBandeira);


            if (paises.length > 0) {
                return paises[0];
            } else {
                return null;
            }

        } catch (Exception e) {
            System.err.println("Algo deu errado: " + e.getMessage());
            return null;
        }
    }

}
