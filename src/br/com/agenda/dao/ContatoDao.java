package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
    //Parametrização que possibilida a moldura do java ao banco
    /*
    CRUD
    C: CREATE
    R: READ
    U: UPDATE
    D: DELETE
     */

    // PARA SALVAR UM CONTATO NO MYSQL:
    // ==== CREATE ====
    public void save(Contato contato){
        String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;

        //Tornar executável
        PreparedStatement pstm = null;
        try{
            //Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //PreparedStatement para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Executar a query
            pstm.execute();
            System.out.println("Contato salvo com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //Fechar as conexões caso estejam abertas
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    // PARA SELECIONAR UM CONTATO NO MYSQL:
    // ==== READ ====
    public List<Contato> getContatos(){
        String sql = "SELECT * FROM contatos";

        List <Contato> contatos = new ArrayList<Contato>();
        Connection conn = null;
        PreparedStatement pstm = null;

        //Fonte de dados
        ResultSet rset = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto tiver dados para percorrer, ele irá
            while(rset.next()){
                Contato contato = new Contato();
                //Recuperar o id
                contato.setId(rset.getInt("id"));
                //Recuperar o nome
                contato.setNome(rset.getString("nome"));
                //Recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                //Recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("dataCadastro"));

                contatos.add(contato);

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return contatos;
    }

    // PARA ATUALIZAR UM CONTATO NO MYSQL
    // ==== UPDATE ====

    public void update (Contato contato){
        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            //Criar conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();
            //Criar a classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores a atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getId());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.setInt(4, contato.getId());

            //Executar
            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // PARA DELETAR UM CONTATO NO MYSQL
    // ==== DELETE ====

    public void deleteById(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
