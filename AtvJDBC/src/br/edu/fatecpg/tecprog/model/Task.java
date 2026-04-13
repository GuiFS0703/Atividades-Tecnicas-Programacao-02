package br.edu.fatecpg.tecprog.model;

import java.util.UUID;

public class Task {
    private final String id;
    private String nome;
    private String categoria;
    private boolean conclusao;
    private String descricao;

    public Task(String nome, String categoria, String descricao) {
        if((nome == null) || nome.trim().isEmpty() || (categoria == null) || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome ou categoria inválida!");
        }
        else {
            String id_completo = UUID.randomUUID().toString();
            this.id = id_completo.substring(0, 14);
            this.nome = nome;
            this.categoria = categoria;
            this.conclusao = false;
            this.descricao = descricao.trim().isEmpty() ? "Sem descrição" : descricao; //n é obrigatorio
        }
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isConclusao() {
        return conclusao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setConclusao(boolean conclusao) {
        this.conclusao = conclusao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", conclusao=" + conclusao +
                '}';
    }
}
