package br.edu.fatecpg.appviacep.view;

import br.edu.fatecpg.appviacep.service.ConsomeApi;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException, InterruptedException {
        try{
            boolean continuar = false;
            do{
                System.out.println("\n=== SISTEMA DE CONSULTA VIA CEP ===");
                System.out.println("1 - Buscar endereço por CEP");
                System.out.println("2 - Listar histórico de consultas");
                System.out.println("3 - Excluir endereço do histórico");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                Scanner scan = new Scanner(System.in);
                int op = scan.nextInt();
                scan.nextLine();
                switch (op) {
                    case 1:
                        System.out.println("Digite um cep: ");
                        String cep = scan.next();
                        String retorno = ConsomeApi.buscaCep(cep);
                        System.out.println("Encontrado! Aqui: " + retorno);
                        break;
                    case 2:
                            String resp = ConsomeApi.mostrarHistorico();
                            System.out.println("Resposta: " + resp);
                        break;
                    case 3:
                            System.out.println("Digite o cep do item a ser removido: ");
                            String remover = scan.nextLine();
                            String resposta = ConsomeApi.deletarItemHistorico(remover);
                            System.out.println(resposta);
                        break;

                    case 0:
                        System.exit(0); //finaliza o programa sem erros
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente!");
                        continuar = true;
                        break;
                }
                if(!continuar) {
                    scan.nextLine();
                    System.out.println("Quer continuar? Aperte 'S' para sim e 'N' para não: ");
                    String resp = scan.nextLine().toUpperCase();
                    if(resp.equals("S")) {
                        continuar = true;
                    }
                }
            }while(continuar);
    } catch (Exception e) {
            System.err.println("Algo deu errado: " + e.getMessage());
        }
    }
}

