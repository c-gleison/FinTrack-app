package utils;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {
        
    // Códigos ANSI para cores no terminal
    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String VERMELHO = "\u001B[31m";
    
    public static String currencyFormatter(double value){ 
        
        NumberFormat formater = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));      
        return formater.format(value);
    }
    
    public static String dateFormatter ( LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formater.format(date);   
    }
    
    public static String typeColor (String type){

        if (type == null) {
            return "";

        }
        if (type.equalsIgnoreCase("Receita")) {
            return VERDE + type + RESET;
        }
        else if (type.equalsIgnoreCase("Despesa")) {
            return VERMELHO + type + RESET;
        }
        
        return type;  
    }
    
}
