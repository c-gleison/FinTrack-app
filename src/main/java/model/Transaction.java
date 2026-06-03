package model;

import java.time.LocalDate;
import utils.Formatter;


public class Transaction {
    
    final String type;
    private final String description;
    private final double value;
    private final LocalDate date;
    
    
    public Transaction (String description, double value, String type){
        
        this.type = type;
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
    }
    
    public String getDescription(){
        return description; 
    }
    
    public double getValue(){
        return value;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public String getType(){    
        return type;
    }
    
    public String toTextBox(int id){    
        return 
            """
             ------------------
             ID: %02d | Descrição: %s 
                    |     Valor: %s
                    |      Data: %s
                    |      Tipo: %s
             ------------------   
            """.formatted(
            id, 
            getDescription(), 
            Formatter.currencyFormatter(getValue()), 
            Formatter.dateFormatter(getDate()),
            Formatter.typeColor(getType().toUpperCase()));        
    }
    
}
