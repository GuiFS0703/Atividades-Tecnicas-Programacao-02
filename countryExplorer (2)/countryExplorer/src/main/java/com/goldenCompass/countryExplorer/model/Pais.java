package com.goldenCompass.countryExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Pais {

    @JsonProperty("common")
    private String nomeComum;
    @JsonProperty("official")
    private String nomeOff;
    private List<String> capital;
    @JsonProperty("region")
    private String regiao;
    @JsonProperty("subregion")
    private String subRegiao;
    @JsonProperty("population")
    private int populacao;
    private int area;
    @JsonProperty("png")
    private String urlBandeira;

    public Pais(String nomeComum, String nomeOff, List<String> capital, String regiao, String subRegiao, int populacao, int area) {
        if(nomeComum == null || nomeComum.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome comum inválido!");
        }else if(nomeOff == null || nomeOff.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome oficial inválido!");
        }else if(capital == null) {
            throw new IllegalArgumentException("Capital inválida!");
        }else if(regiao == null || regiao.trim().isEmpty()) {
            throw new IllegalArgumentException("Região inválida!");
        }else if(subRegiao == null || subRegiao.trim().isEmpty()) {
            throw new IllegalArgumentException("Subregião inválida!");
        } else if(area <=0) {
            throw new IllegalArgumentException("Área inválida!");
        } else if(populacao<=0) {
            throw new IllegalArgumentException("População inválida!");
        } else {
            this.nomeComum = nomeComum;
            this.nomeOff = nomeOff;
            this.capital = capital;
            this.regiao = regiao;
            this.subRegiao = subRegiao;
            this.populacao = populacao;
            this.area = area;
            this.urlBandeira = null; //ta aninhado e vai dar erro pq n vai achar. Arrumar manualmente.
        }
    }

    public String getNomeComum() {
        return nomeComum;
    }

    public void setNomeComum(String nomeComum) {
        if(nomeComum == null || nomeComum.trim().isEmpty()) {
            System.err.println("Nome comum inválido!");
        } else {
            this.nomeComum = nomeComum;
        }}

    public String getNomeOff() {
        return nomeOff;
    }

    public void setNomeOff(String nomeOff) {
        if(nomeOff == null || nomeOff.trim().isEmpty()) {
            System.err.println("Nome oficial inválido!");
        } else {
            this.nomeOff = nomeOff;
        }
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        if(capital == null || capital.trim().isEmpty()) {
            System.err.println("Capital inválida!");
        } else {
            this.capital.add(capital);
        }
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        if(regiao == null || regiao.trim().isEmpty()) {
            System.err.println("Região inválida!");
        } else {
            this.regiao = regiao;
        }
    }

    public String getSubRegiao() {
        return subRegiao;
    }

    public void setSubRegiao(String subRegiao) {
        if(subRegiao == null || subRegiao.trim().isEmpty()) {
            System.err.println("Subregião inválida!");
        } else {
            this.subRegiao = subRegiao;

        }
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        if(populacao<=0) {
            System.err.println("População inválida!");
        } else {
            this.populacao = populacao;
        }
    }

    public String getUrlBandeira() {
        return urlBandeira;
    }

    public void setUrlBandeira(String urlBandeira) {
        if(urlBandeira == null || urlBandeira.trim().isEmpty()) {
            System.err.println("Url inválida!");
        } else {
            this.urlBandeira = urlBandeira;
        }
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        if(area <=0) {
            System.err.println("Área inválida!");
        } else {
            this.area = area;
        }
    }

    @Override
    public String toString() {
        return "Pais{" +
                "nomeComum='" + nomeComum + '\'' +
                ", nomeOff='" + nomeOff + '\'' +
                ", capital='" + capital + '\'' +
                ", regiao='" + regiao + '\'' +
                ", subRegiao='" + subRegiao + '\'' +
                ", populacao=" + populacao +
                ", area=" + area +
                ", urlBandeira='" + urlBandeira + '\'' +
                '}';
    }
}
