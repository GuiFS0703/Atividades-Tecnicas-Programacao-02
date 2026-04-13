package br.edu.fatecpg.tecprog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static final String url = "jdbc:sqlite:agendaTask.db"; //estatica pq n precisa de instância dela pra usar tal atributo e o  final pra ser imutavel

    public static Connection conectar() { //retorna connection
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conexão feita com sucesso!!!");
            return conn;
        }catch(SQLException e) {
            System.err.println("Erro encontrado: " + e.getMessage() );
            return null;
        }
    }
}
