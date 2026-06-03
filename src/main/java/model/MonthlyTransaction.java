package model;

import utils.Formatter;

public class MonthlyTransaction extends Transaction {
    
    // Variaveis
    private final String month;

    // Constructor 
    public MonthlyTransaction(String description, double value, String type, String month) {
        super(description, value, type);
        this.month = month;
         
    }
    
    // Getters e Setters
    public String getMonth() {
        return month;
    }
    
    @Override
    public String toTextBox(int id){
        return 
            """
             ------------------
             ID: %02d | Descrição: %s 
                    |     Valor: %s
                    |      Data: %s
                    |      Tipo: %s
                    |----------
                    | RECORRENTE
                    |       Mês: %s
             ------------------   
            """.formatted(
            id, 
            getDescription(), 
            Formatter.currencyFormatter(getValue()), 
            Formatter.dateFormatter(getDate()),
            Formatter.typeColor(getType().toUpperCase()),
            getMonth().toUpperCase());
    }

}
