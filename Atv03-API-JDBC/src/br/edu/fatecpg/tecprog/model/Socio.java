package br.edu.fatecpg.tecprog.model;

import com.google.gson.annotations.SerializedName;

public class Socio {
    @SerializedName("cnpj_cpf_do_socio") private String doc_socio;
    @SerializedName("qualificacao_socio") private String cargo_socio;
    private String nome_socio;
    @SerializedName("data_entrada_sociedade") private String data_entrada;

    public Socio(String doc_socio, String cargo_socio, String nome_socio, String data_entrada) {
        if(doc_socio == null || doc_socio.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento nulo ou vazio!");
        } else if(cargo_socio == null || cargo_socio.trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo nulo ou vazio!!!");
        } else if(nome_socio == null || nome_socio.trim().isEmpty()){
            throw new IllegalArgumentException("Nome nulo ou vazio!!!");
        } else if(data_entrada == null || data_entrada.trim().isEmpty()) {
            throw new IllegalArgumentException("Data nula ou vazia!!!");
        } else {
            this.doc_socio = doc_socio;
            this.cargo_socio = cargo_socio;
            this.nome_socio = nome_socio;
            this.data_entrada = data_entrada;
        }
    }

    public String getDoc_socio() {
        return doc_socio;
    }

    public String getCargo_socio() {
        return cargo_socio;
    }

    public String getNome_socio() {
        return nome_socio;
    }

    public String getData_entrada() {
        return data_entrada;
    }


    public void setDoc_socio(String doc_socio) {
        if(doc_socio == null || doc_socio.trim().isEmpty()) {
            System.err.println("Documentação do sócio vazia ou nula!!!");
        } else {
            this.doc_socio = doc_socio;
        }
    }

    public void setCargo_socio(String cargo_socio) {
        if(cargo_socio == null || cargo_socio.trim().isEmpty()) {
            System.err.println("Cargo do sócio nulo ou vazio!!!");
        } else {
            this.cargo_socio = cargo_socio;
        }

    }

    public void setNome_socio(String nome_socio) {
        if(nome_socio == null || nome_socio.trim().isEmpty()) {
            System.err.println("Nome sócio inválido ou vazio!!!");
        } else {
            this.nome_socio = nome_socio;
        }
    }

    public void setData_entrada(String data_entrada) {
        if(data_entrada == null || data_entrada.trim().isEmpty()) {
            System.err.println("Data de entrada nula ou vazia!!!");
        }else {
            this.data_entrada = data_entrada;
        }
    }

    @Override
    public String toString() {
        return "Socio{" +
                "doc_socio='" + doc_socio + '\'' +
                ", cargo_socio='" + cargo_socio + '\'' +
                ", nome_socio='" + nome_socio + '\'' +
                ", data_entrada='" + data_entrada + '\'' +
                '}';
    }
}