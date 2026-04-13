package br.edu.fatecpg.tecprog.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
    public static final String URL = "jdbc:postgresql://localhost:5432/meubanco";
    public static final String USER = "guilhermesaltao";
    public static final String SENHA = "5gcA7WRPBPbi3e";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, SENHA);
        } catch (SQLException e) {
            System.err.println("Algo deu errado: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabelas() {
        try(Connection conn = ConnectDB.getConnection();) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS empresas ("
                    + "cnpj VARCHAR(14) PRIMARY KEY, "
                    + "razao_social VARCHAR(200), "
                    + "nome_fantasia VARCHAR(200), "
                    + "logradouro VARCHAR(200))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS socios (\n" +
                    "    id SERIAL PRIMARY KEY,\n" + //serial é o auto_increment
                    "    nome_socio VARCHAR(200) NOT NULL,\n" +
                    "    doc_socio VARCHAR(20),\n" +
                    "    cargo_socio VARCHAR(100),\n" +
                    "    data_entrada DATE,\n" +
                    "    empresa_cnpj VARCHAR(14) NOT NULL,\n" +
                    "    FOREIGN KEY (empresa_cnpj) REFERENCES empresas(cnpj) ON DELETE CASCADE\n" +
                    ");");
            System.out.println("Deu certo!!!!");
        }catch(SQLException | NullPointerException e) {
            System.err.println("Algo deu errado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try(Connection conn = getConnection()) {
            System.out.println("Deu certo!!!");
        }catch(SQLException e) {
            System.err.println("Algo deu errado: " + e.getMessage());
        }
    }

}
