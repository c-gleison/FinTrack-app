package controller;

import utils.Formatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.MonthlyTransaction;
import model.Transaction;

public class FinTracker {
    
    //Variaveis globais
    ArrayList<Transaction> transactionsList = new ArrayList<>();

    public void addTransaction(Scanner scan){
        
        // DESCRIÇÃO
        // Logica para receber a descrição e os valores da transação
        System.out.print(" Descrição da transação: ");
        String description = "";
        
        // Validação para impedir que a descrição esteja vazia
        while (description.isBlank()) {  
            
            description = scan.nextLine(); 
            
            if (description.isEmpty()) {
                
                System.out.print(" Informe uma descrição: ");
            }
        }
        System.out.println(); //Espaço de uma linha
        
        // TIPO (RECEITA OU DESPESA)
        String type = " ";
        System.out.println(" Informe o tipo da transação.");
        
        boolean validType = false; 
        while (!validType){
            
            System.out.print(" Receita ou Despesa: ");
            type = scan.nextLine().trim();

            // Evitar resposta vazia
            if (type.isEmpty()) {     
          
                continue;     
            }
            
            // Validação das opções aceitas
            if (type.equalsIgnoreCase("Receita") || type.equalsIgnoreCase("Despesa")) {

                validType = true;

            } else  {
                // Se digitou qualquer outra coisa (ou deixou vazio) cai aqui
                System.out.println();
                System.out.print(" Informe o tipo correto! ");
                System.out.println();
            }
        }
        
        System.out.println(); //Espaço de uma linha
        
        // RECORRENCIA
        boolean itsRecurring = false;
        String month = "";
        String response = "";
        boolean validResponse = false;
        
        while(response.isBlank() || !validResponse){
            System.out.println(" Essa transação é recorrente?");
            System.out.print(" (Se repete todo mês?): ");
            response = scan.nextLine().trim();
            
            // Evitar resposta vazia
            if (response.isEmpty()) {
                continue;                             
            }
            
            // Validar repostas aceitas
            if (response.equalsIgnoreCase("S")) {
                System.out.print(" Mês: ");
                month = scan.nextLine();
                
                itsRecurring = true; // Define a transação como recorrente
                validResponse = true; // Resposta valida
                
            }else if (response.equalsIgnoreCase("N")) {
                validResponse = true; // Resposta valida
                
            }else{
                
                System.out.println(" Por favor, responda \"S\" ou \"N\""); 
                System.out.println();
            }
          
        }    
        System.out.println(); //Espaço de uma linha
            
        // VALOR
        double value = 0;
        boolean validEntry = false;
        
        // Validação para impedir que o valor esteja vazio
        while (!validEntry){
            
            System.out.print(" Valor da transação: R$ ");  
            String sValue = scan.nextLine().trim();
             
            // Retorna ao inicio do loop caso a string esteja vazia
            if (sValue.isEmpty()) {
                continue;
            }
            
            // Validação para aceitar apenas valores numéricos
            try {
                    sValue = sValue.replace(",", ".");
                    value = Double.parseDouble(sValue);
                    
                    //Verifica se o valor é positivo.
                    if (value > 0) {
                        
                        validEntry = true;  
                        
                    }else{
                        
                        System.out.println();
                        System.out.println(" O valor deve ser maior que R$ 0,00. ");
                        System.out.println();                 
                    }
           
            }catch(NumberFormatException e){ 
                
                System.out.println(); 
                System.out.println(" Informe um valor numerico. ");
                System.out.println(); 
     
            }
        }
        System.out.println(); //Espaço de uma linha
        
        // ARMAZENAMENTO DE INFORMAÇÕES
        
        // Variavel local de referência para o objeto
        Transaction newTransaction;
        
        if (itsRecurring) {
            // Salvar informações da transação no objeto 
            newTransaction = new MonthlyTransaction(description, value, type, month);
            
            // Adiciona a transação na lista
            transactionsList.add(newTransaction);
            
        }else{
            // Salvar informações da transação no objeto 
            newTransaction = new Transaction(description, value, type);

            // Adiciona a transação na lista
            transactionsList.add(newTransaction);
        }

        // Mensagem de confirmação
        System.out.println
        ("""
         \s
          +-=-=-=-=-=-=-=-=-=-=-=-+
          | TRANSAÇÃO ADICIONADA! |
          +-=-=-=-=-=-=-=-=-=-=-=-+  
         \s                  
         """);
      
    }

    public void removeTransaction(Scanner scan){
        
        if (transactionsList.isEmpty()) {
            System.out.println();
            System.out.println(" Não existem transações salvas! ");
            System.out.println();
            return;
        }
        
        while(true){
            
            System.out.print(" Digite o ID da transação a ser removida: ");
            int index = scan.nextInt();
            scan.nextLine(); // Limpar buffer
            System.out.println();  
            
            // Validação para impedir que o usuario tente informar um valor de ID inexistente.
            if (index >= 0 && index < transactionsList.size()) {
                
                transactionsList.remove(index);
                System.out.println(" Transação de ID: " + index + " removida com sucesso!");
                System.out.println();        
                break; //Encerra o loop
                
            } else {
                
                System.out.println(" ID inexistente, tente novamente.");
                System.out.println();
                
            }
        }
    }
    
    public void listAllTransactions(){
       
        if (transactionsList.isEmpty()) {
            
            System.out.println(" Nenhuma transação registrada. ");
            System.out.println();
            
        }else{
        
            for (int i = 0; i < transactionsList.size(); i++)
            { 
                Transaction t = transactionsList.get(i);
                System.out.printf(t.toTextBox(i));

            }
        }
        
        System.out.println();   
    }
     
    public void totalBalance(){
        
        double currentT = 0; //Variavel para guardar o valor acumulado das transações
        
        for (Transaction t : transactionsList) {  
            
            if (t.getType().equalsIgnoreCase("Receita")) {
                currentT += t.getValue();
            }else{
                currentT -= t.getValue();
            }

        } 
        
        System.out.println();
        System.out.printf(" SALDO TOTAL: %s", Formatter.currencyFormatter(currentT));
        System.out.println();
        System.out.println(); 

    } 
}
