package br.edu.fatecpg.tecprog.view;

import br.edu.fatecpg.tecprog.bd.ConnectDB;
import br.edu.fatecpg.tecprog.model.Empresa;
import br.edu.fatecpg.tecprog.model.Socio;
import br.edu.fatecpg.tecprog.repository.ConsomeApi;
import com.google.gson.Gson;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //ConnectDB.criarTabelas(); Criando aq.........................
        boolean repetir = true;
        Scanner scan = new Scanner(System.in);
        String op = "";
        do {
            System.out.println("\n=== SISTEMA DE CADASTRO DE EMPRESAS ===");
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║  1 - Cadastrar nova empresa          ║");
            System.out.println("║  2 - Listar empresas cadastradas     ║");
            System.out.println("║  0 - Sair                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            op = scan.nextLine().trim();
            Gson gson = new Gson();
            switch(op) {
                case "1":
                    System.out.println("Digite o cnpj da empresa a ser add: ");
                    String cnpj = scan.nextLine().trim();
                    String resp = ConsomeApi.buscarempresa(cnpj);
                    if(resp.contains("Cnpj inválido!")) {
                        System.out.println(resp);
                    } else {
                        Empresa empresa = gson.fromJson(resp, Empresa.class);
                        System.out.println(empresa);
                        try(Connection conn = ConnectDB.getConnection();) { //prepareStatement tem placeholders
                            PreparedStatement pstmt = conn.prepareStatement("insert into empresas (cnpj, razao_social, nome_fantasia, " +
                                    "logradouro) VALUES(?, ?, ?, ?)");
                            pstmt.setString(1, empresa.getCnpj());
                            pstmt.setString(2, empresa.getRazao_social());
                            pstmt.setString(3, empresa.getNome_fantasia());
                            pstmt.setString(4, empresa.getLogradouro());
                            pstmt.executeUpdate();
                            for(Socio socio : empresa.getSocio()) {
                                PreparedStatement pstmt2 = conn.prepareStatement("insert into socios (nome_socio, doc_socio, cargo_socio, " +
                                        "data_entrada, empresa_cnpj) VALUES (?, ?, ?, ?, ?)");
                                pstmt2.setString(1, socio.getNome_socio());
                                pstmt2.setString(2, socio.getDoc_socio());
                                pstmt2.setString(3, socio.getCargo_socio());
                                pstmt2.setDate(4, java.sql.Date.valueOf(socio.getData_entrada())); //converter p date
                                pstmt2.setString(5, empresa.getCnpj());
                                pstmt2.executeUpdate();
                                System.out.println("Criado: " + socio.toString());
                            }

                        }catch(SQLException e) {
                            System.err.println("Algo deu errado: " + e.getMessage());
                        }
                    }
                    break;
                case "2":
                    try(Connection conn = ConnectDB.getConnection();) {
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from empresas");
                        while(rs.next()) {
                            System.out.println("CNPJ: " + rs.getString("cnpj") + " | " + "Razão: " +
                                    rs.getString("razao_social") + " | " + "Logradouro: " + rs.getString("logradouro"));
                        }
                    }catch(SQLException e) {
                        System.out.println("Algo deu errado: " + e.getMessage());
                    }
                    break;
                case "0":
                    repetir = false;
                    break;

                default:

                    break;
            }
        }while(repetir);

    }

}