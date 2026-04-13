package br.edu.fatecpg.tecprog.view;

import br.edu.fatecpg.tecprog.db.*;
import br.edu.fatecpg.tecprog.model.*;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    public static void main(String[] args) {
        try(Connection conn = ConnectDB.conectar()) {
            DatabaseSetup.createTaskTable(); //cria tabela se não existir

            boolean continuar = true; // ← MUDEI DE false PRA true
            Scanner scan = new Scanner(System.in);

            do {
                System.out.println("\n=== SISTEMA DE TAREFAS ===");
                System.out.println("1 - Criar tarefa");
                System.out.println("2 - Visualizar tarefas");
                System.out.println("3 - Atualizar tarefa");
                System.out.println("4 - Deletar tarefa");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");

                int resposta = scan.nextInt();
                scan.nextLine(); //limpa buffer

                switch(resposta) {
                    case 1:
                        System.out.println("Digite o nome da task: ");
                        String nome = scan.nextLine().trim();
                        System.out.println("Digite a categoria: ");
                        String categoria = scan.nextLine().trim();
                        System.out.println("Digite a descrição: ");
                        String descricao = scan.nextLine().trim();

                        Task task = new Task(nome, categoria, descricao);
                        String query = "INSERT INTO tarefas (id, titulo, descricao, categoria, concluida) VALUES (?, ?, ?, ?, ?)";

                        try(PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, task.getId());
                            stmt.setString(2, task.getNome());
                            stmt.setString(3, task.getDescricao());
                            stmt.setString(4, task.getCategoria());
                            stmt.setInt(5, task.isConclusao() ? 1 : 0);

                            int linhasAfetadas = stmt.executeUpdate();
                            if(linhasAfetadas > 0) {
                                System.out.println("Tarefa inserida!");
                                System.out.println(task.toString());
                            } else {
                                System.out.println("Nenhuma tarefa foi inserida!");
                            }
                        } catch(SQLException e) {
                            System.out.println("Criação de task deu b.o: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Tarefas armazenadas: ");
                        try(Statement stmt = conn.createStatement();
                            ResultSet resu = stmt.executeQuery("SELECT * FROM tarefas")) {

                            while(resu.next()) {
                                String id = resu.getString("id");
                                String titulo = resu.getString("titulo");
                                String desc = resu.getString("descricao");
                                boolean concluida = resu.getInt("concluida") == 1; // ← SIMPLIFIQUEI
                                String categ = resu.getString("categoria");
                                System.out.println("Id: " + id + " | Nome: " + titulo + " | Descrição: " + desc + " | Categoria: " + categ + " | Status: " + (concluida ? "✅" : "❌"));
                            }
                        } catch(SQLException e) {
                            System.err.println("Erro na listagem: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // LISTAR PRIMEIRO
                        try(Statement stmt = conn.createStatement();
                            ResultSet resu = stmt.executeQuery("SELECT id, titulo FROM tarefas")) {

                            System.out.println("\nTarefas disponíveis:");
                            while(resu.next()) {
                                System.out.println("ID: " + resu.getString("id") + " | " + resu.getString("titulo"));
                            }
                        } catch(SQLException e) {
                            System.err.println("Erro: " + e.getMessage());
                            break;
                        }

                        System.out.print("Digite o ID da tarefa: ");
                        String idUpdate = scan.nextLine();

                        System.out.println("Digite uma das opções para alterar: (titulo, descricao, categoria, status) ");
                        String op = scan.nextLine().toLowerCase();

                        System.out.print("Digite o novo valor (se for status: 0 pra em analise e 1 pra finalizado): ");
                        String newValor = scan.nextLine();

                        String updateQuery = "";
                        switch(op) {
                            case "titulo":
                                updateQuery = "UPDATE tarefas SET titulo = ? WHERE id = ?";
                                break;
                            case "descricao":
                                updateQuery = "UPDATE tarefas SET descricao = ? WHERE id = ?";
                                break;
                            case "categoria":
                                updateQuery = "UPDATE tarefas SET categoria = ? WHERE id = ?";
                                break;
                            case "status":
                                updateQuery = "UPDATE tarefas SET concluida = ? WHERE id = ?";
                                // Converte string para 0/1
                                int status = 0;
                                if(newValor.equals("1")) {
                                    status = 1;
                                }
                                try(PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                                    pstmt.setInt(1, status);
                                    pstmt.setString(2, idUpdate);
                                    int linhas = pstmt.executeUpdate();
                                    System.out.println(linhas > 0 ? "Atualizado!" : "Falhou");
                                }
                                break;
                            default:
                                System.out.println("Opção inválida!!");
                                continue;
                        }

                        // Se não for status, executa update normal
                        if(!op.equals("status") && !updateQuery.isEmpty()) {
                            try(PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                                pstmt.setString(1, newValor);
                                pstmt.setString(2, idUpdate);
                                int linhas = pstmt.executeUpdate();
                                System.out.println(linhas > 0 ? "✅ Atualizado!" : "❌ Falhou");
                            }
                        }
                        break;

                    case 4:
                        // LISTAR PRIMEIRO
                        try(Statement stmt = conn.createStatement();
                            ResultSet resu = stmt.executeQuery("SELECT id, titulo FROM tarefas")) {

                            System.out.println("\nTarefas disponíveis:");
                            while(resu.next()) {
                                System.out.println("ID: " + resu.getString("id") + " | " + resu.getString("titulo"));
                            }
                        } catch(SQLException e) {
                            System.err.println("Erro ao buscar ids: " + e.getMessage());
                            break;
                        }

                        System.out.print("Digite o ID da tarefa a ser deletada: ");
                        String id = scan.nextLine();

                        String deleteQuery = "DELETE FROM tarefas WHERE id = ?";
                        try(PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                            pstmt.setString(1, id);
                            int linhas = pstmt.executeUpdate();
                            System.out.println(linhas > 0 ? "Tarefa deletada!" : "Nada foi deletado!");
                        } catch(SQLException e) {
                            System.err.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Adeus!");
                        System.exit(0);

                    default:
                        System.out.println("Opção inválida! Tente novamente!");
                }

            } while(continuar); // ← AGORA FUNCIONA

        } catch(InputMismatchException e) {
            System.err.println("Dados incompatíveis!");
        } catch(SQLException e) {
            System.err.println("Erro no banco: " + e.getMessage());
        } catch(Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}