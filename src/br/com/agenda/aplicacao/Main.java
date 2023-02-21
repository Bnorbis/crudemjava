package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDao;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // #### CREATE ####
        // ### INSERT ###
        ContatoDao contatoDao = new ContatoDao();

        Contato contato = new Contato();
        contato.setNome("André Alves");
        contato.setIdade(21);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato);

        //Visualização dos registro do banco de dados TODOS
        // #### SELECT ####
        // ### READ ###
        for(Contato c : contatoDao.getContatos()){
            System.out.println("Contato: "+c.getNome());
        }

        // Atualizar um contato
        // #### UPDATE ####

        Contato c1 = new Contato();
        c1.setNome("Maria Gabriela Dias Orlando");
        c1.setIdade(37);
        c1.setDataCadastro(new Date());
        c1.setId(1);// Número no banco de dados

        contatoDao.update(c1);

        // Remover o contato
        // #### DELETE ####
        contatoDao.deleteById(2);

    }
}
