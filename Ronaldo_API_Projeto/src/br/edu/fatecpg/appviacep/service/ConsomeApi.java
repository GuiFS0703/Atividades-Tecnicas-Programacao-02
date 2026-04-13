package br.edu.fatecpg.appviacep.service;
import br.edu.fatecpg.appviacep.model.Endereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsomeApi {
    private static List <Endereco> historico = new ArrayList<>();
    private static Gson gson = new Gson();

    public static String buscaCep(String end) {
        try {
            if(end == null || end.trim().isEmpty() ) {
                System.out.println("Endereço nulo ou vazio!");
                return null;
            }
            else if(!historico.isEmpty()) {
                for(Endereco endereco : historico) {
                    if(end.equals(endereco.getCep())) {
                        return endereco.toString();
                    }
                }
                String cep = "https://viacep.com.br/ws/" + end + "/json/";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder().uri(URI.create(cep)).build();
                HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                if(resp.body().contains("Bad Request") || resp.body().contains("<!DOCTYPE")) {
                    System.out.println("Cep inválido!!!");
                    return null;
                } else {
                    Endereco endereco = gson.fromJson(resp.body(), Endereco.class);
                    historico.add(endereco);
                    return resp.body();
                }
            } else {
                HttpClient client = HttpClient.newHttpClient(); //configura conexão, envia req.....
                String cep = "https://viacep.com.br/ws/" + end + "/json/"; //minha uri
                HttpRequest req = HttpRequest.newBuilder().uri(URI.create(cep)).build();
                HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString()); //usa client pra enviar a
                // req e armazena a resp que está convertida pra String
                if(resp.body().contains("Bad Request") || resp.body().contains("<!DOCTYPE")) { //ele vem em html se vier com erro
                    System.out.println("Cep inválido!!!");
                    return null;
                } else {
                    Endereco endereco = gson.fromJson(resp.body(), Endereco.class); //convertendo
                    historico.add(endereco); //sem this pq referencia atibuto ou método do objeto.
                    return resp.body(); //só pra exibir.
                }
            }
        }catch(IOException | InterruptedException e) { //uma exceção ou outra
            System.err.println("Algo deu errado: " + e.getMessage());
            return null;
        }
    }

    public static String mostrarHistorico() {
        if(historico.isEmpty()) {
            return "Não há itens no histórico!!";
        } else {
            return historico.toString();
        }
    }

    public static String deletarItemHistorico(String cep) {
        if(cep == null || cep.trim().isEmpty()) {
            return "Cep inválido!!!";
        }
        else if(historico.isEmpty()) {
            return "Não há itens no histórico!!";
        }
        else {
            for (int i = 0; i<historico.size(); i++) {
                if(historico.get(i).getCep().replace("-", "").equals(cep.replace("-", ""))) //remove '-' e substitui por "", ou seja, nada.
                {
                    historico.remove(i);
                    return "Item removido com sucesso!!!";
                }
            }
        }
            return "Esse item não está no histórico!";
    }
}
