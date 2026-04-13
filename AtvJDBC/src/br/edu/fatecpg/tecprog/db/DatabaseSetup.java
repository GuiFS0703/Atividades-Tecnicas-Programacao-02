package br.edu.fatecpg.tecprog.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTaskTable() {
        String query = "CREATE TABLE IF NOT EXISTS tarefas (id TEXT PRIMARY KEY, titulo TEXT NOT NULL, descricao TEXT, concluida INTEGER DEFAULT 0)";

        try(Connection conn = ConnectDB.conectar(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
            System.out.println("Tabela criada com sucesso!!!");
        } catch (SQLException e) {
            System.err.println("Algo deu errado: " + e.getMessage());
        }
    }

    public static void atualizarTable() {
        String query = "ALTER TABLE tarefas ADD COLUMN categoria TEXT;";
        try(Connection conn = ConnectDB.conectar(); Statement stmt = conn.createStatement();) {
            stmt.execute(query);
            System.out.println("Tabela alterada com sucesso!");
        }catch(SQLException e) {
            System.err.println("Algo deu errado: " + e.getMessage());
        }
    }
}
