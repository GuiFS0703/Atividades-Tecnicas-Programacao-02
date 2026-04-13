package br.edu.fatecpg.tecprog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Empresa {
    private String nome_fantasia;
    private String logradouro;
    private String cnpj;
    private String razao_social;
    @SerializedName("qsa")
    private List<Socio> socios;

    public Empresa(String nome_fantasia, String logradouro, String cnpj, String razao_social, List<Socio> socios) {
        if(nome_fantasia == null || nome_fantasia.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome nulo ou vazio!!!");
        } else if(logradouro == null || logradouro.trim().isEmpty()) {
            throw new IllegalArgumentException("Logradouro nulo ou vazio!!!");
        }else if(cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("Cnpj nulo ou vazio!");
        } else if(razao_social == null || razao_social.trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social nula ou vazia!!!");
        }else if(socios == null) {
            throw new IllegalArgumentException("Sócios inválidos!!!");
        } else {
            this.nome_fantasia = nome_fantasia;
            this.logradouro = logradouro;
            this.cnpj = cnpj;
            this.razao_social = razao_social;
            this.socios = socios;
        }
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nomeFantasia) {
        if(nomeFantasia == null || nomeFantasia.trim().isEmpty()) {
            System.err.println("Nome vazio ou nulo!");
        } else {
            this.nome_fantasia = nome_fantasia;
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if(logradouro == null || logradouro.trim().isEmpty()) {
            System.err.println("Logradouro vazio ou nulo!!!");
        } else {
            this.logradouro = logradouro;
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(cnpj == null || cnpj.trim().isEmpty()) {
            System.err.println("Cnpj nulo ou vazio!!!");
        } else {
            this.cnpj = cnpj;
        }
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        if (razao_social == null || razao_social.trim().isEmpty()) {
            System.err.println("Razão social vazia ou nula!!!!");
        }  else {
            this.razao_social = razao_social;
        }
    }

    public List<Socio> getSocio() {
        return socios;
    }

    public void setSocio(Socio socio) {
        if(socio == null) {
            System.err.println("Sócio nulo!!!");
        } else {
            this.socios.add(socio);
            System.out.println("Adicionado: " + socio.toString());
        }
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nome_fantasia='" + nome_fantasia + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", razao_social='" + razao_social + '\'' +
                ", socios=" + socios +
                '}';
    }
}
