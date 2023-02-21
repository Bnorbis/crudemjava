package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    //Classe destinada a conexão entre banco de dados e código

    //Nome do usuário do mysql
    private static final String USERNAME = "root";

    //Senha do banco
    private static final String PASSWORD = "55746";

    //Caminho do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    //Criar conexão com o MYSQL
    public static Connection createConnectionToMySQL() throws Exception{
        //Classe carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Cria a conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    //Para não estressar o servidor
    public static void main(String[] args) throws Exception {
        //Recuperar uma conexão com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testar se a conexão é nula
        if(con!=null){
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        }
    }

}
