# FinTrack

## Descrição
Projeto feito para o [Capacita Brasil - iRede ](https://capacitabrasil.irede.org.br/) , como parte do modulo inicial da disciplina de Java.<br>
Consiste em uma aplicação de Finanças pessoais, onde o usuário pode gerenciar transações financeiras.

## Funcionalidades
**É possivel:**
- Adicionar transação (receita ou despesa)
- Remover transação
- Listar todas as transações
- Ver saldo total

## Conceitos usados
Foram usados conceitos da programação orientada a objetos - POO, como:
- Classes
- Herança
- Metodos
- Encapsulamento
- Polimorfismo / Sobrescrita

## Programação
- Estruturas condicionais (`if-else`, `switch-case`)
- Laços de repetição (`while`, `for`)
- Tratamento de exceções customizadas (`try-catch`)
- Estruturas de dados (`ArrayList`)
- Getters e Setters

## Estrutura
A aplicação usa a arquitetura MVC

```text
├── java/
    ├── controller/
    │   └── FinTracker.java
    ├── exceptions/
    │   └── InvalidEntryException.java
    ├── main/
    │   └── App.java    <--- CLASSE PRINCIPAL
    ├── model/
    │   ├── Transaction.java
    │   └── MonthlyTransaction.java
    └── utils/  
        └── Formatter.java
```

## Como executar o projeto
Certifique-se de ter o Java Development Kit (JDK 11 ou superior) instalado em sua máquina.

1 - Clone o repositório ou baixe em formato .zip.<br>

2 - Abra o prompt de comando (CMD) na raiz do projeto (onde está o arquivo README.md).<br>

3 - Compile o projeto utilizando o [Maven](https://maven.apache.org/download.cgi) pelo prompt de comando, ou através da sua IDE de preferência:<br>

No prompt de comando usando o Maven: 
```text
    mvn clean install
```

4 - Inicie a aplicação executando o arquivo .jar que foi gerado:

No prompt de comando: 
```text
    java -jar target/FinTrack-app.jar
```



