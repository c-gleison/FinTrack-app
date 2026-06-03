package main;


import java.util.Scanner;
import java.util.InputMismatchException;

import controller.FinTracker;
import java.nio.charset.StandardCharsets;

//https://www.youtube.com/watch?v=wgBDJn1OBMc


public class App { 

    public static void main(String[] args)
    {
        
        //Variaveis globais
        boolean quit = false;
        var scan = new Scanner (System.in, StandardCharsets.UTF_8);  

        //Instancias de classes
        App app = new App(); 
        FinTracker finTracker = new FinTracker();
        
        

        // Rodar a aplicação até que o usuario selecione a opção Sair    
        while(quit == false)
        {   
            clearConsole(); // Limpar console   
            app.menu(); // Chama o menu inicial
            
            //Armazena a  opção selecionada pelo usuario
            int option = app.selectOption(scan); 
            scan.nextLine(); // Limpar buffer
            
            clearConsole(); // limpar console
            
            System.out.println(Header());
            
            // Mostra a opção selecionada pelo usuário
            System.out.printf
            ("""                  
              Você selecionou:
              %s
              %s
              %s
              \s
             """,
                "=".repeat(OPTIONS[option -1].length()),// Linhas superiores
                OPTIONS[option -1],                     // Opção selecionada
                "=".repeat(OPTIONS[option -1].length()) // Linhas inferiores
            );

            switch (option) {
                case 1 -> { // Adicionar nova transação
                    finTracker.addTransaction(scan);
                    goBackToHome(scan);
                }
                case 2 -> { // Remover transação                        
                    finTracker.removeTransaction(scan);
                    goBackToHome(scan);
                }
                case 3 -> { // Listar todas as transações
                    finTracker.listAllTransactions();
                    goBackToHome(scan);                      
                }
                case 4 -> { // Saldo total
                    finTracker.totalBalance();
                    goBackToHome(scan);
                }
                case 5 -> { // Sair
                    clearConsole(); //limpar console
                    quit = true;
                }
                default -> throw new AssertionError();
            }   
                           
        }  
    }
    
    // Lista de opções do menu
    private static final String[] OPTIONS = {
            
            "1 - Adicionar nova transação", 
            "2 - Remover transação",  
            "3 - Listar transações", 
            "4 - Saldo atual", 
            "5 - Sair"
        };
    
    public static final String Header(){ 

        return 
        """ 
         ==============================================
         ===== FINTRACK - SEU CONTROLE FINANCEIRO =====
         ==============================================
         ==============================================
        """; 
    }
    
    // Menu inicial
    public void menu() {
        
        System.out.println(Header());
        System.out.println("  --------------------------------------------");
                
        // Loop para imprimir as informações na tela
        for (int i = 0; i < OPTIONS.length; i++) {
           
            /*Define o tamanho lateral da caixa 
              para manter as linhas a direita alinhadas */
            int wLength = 37 - OPTIONS[i].length();
                    
            System.out.print(" |       ");
            System.out.print(OPTIONS[i]);
            System.out.println(" ".repeat(wLength) + "| ");
        }
        
        System.out.println("  --------------------------------------------");
        System.out.println();
    }
    
    // Valida a retorna a opção digitada pelo usuário
    public int selectOption(Scanner scan){
        
        int option = 0;
        
        while(true) {         
            System.out.print(" Selecione a opção desejada: ");   
            try{
                option = scan.nextInt();
                
                    /*Verifica se a opção digitada pelo usuário 
                    está entre o intervalo esperado*/
                    if (option >= 1 && option <= OPTIONS.length) {  
                        
                        return option; 
                        
                    }else{
                        
                        System.out.println(" Selecione uma opção valida!");         
                    }        
            }catch(InputMismatchException ex){
                System.out.println(" Erro: Apenas numeros são aceitos!");
                scan.nextLine();
            }           
        }
    }
    
    /* Metodo para limpar toda a tela do console
       Deve funcionar em Windows, Linux e macOS
    */
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Limpa o CMD do Windows usando ProcessBuilder
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpar o terminal do Linux/Unix/macOS usando codigos ANSI
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: imprime muitas linhas vazias caso os codigos anteriores falhem
            for (int i = 0; i < 50; i++) System.out.println();
        }   
    }
    
    // "Botão" para voltar ao menu inicial
    public static int goBackToHome(Scanner scan){
        
        System.out.println
        ("""
          ==============================================
          +-------------+
          | 1 - VOLTAR  |
          +-------------+
          ==============================================
         """);   
        int returnValue = 0;
    
        while (true) {      
            try{
                System.out.print(" => ");
                int choice = scan.nextInt();
                    if (choice == 1) {
                        return returnValue;
                    }else{
                        System.out.println(" 1 para voltar ");
                    }
            }catch(InputMismatchException ex){
                System.out.println(" Erro: caractere inválido! "); 
                scan.nextLine();
            }  
        }        
    }

}
