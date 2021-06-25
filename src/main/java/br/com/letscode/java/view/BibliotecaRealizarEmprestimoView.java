package br.com.letscode.java.view;

import br.com.letscode.java.dominio.*;
import br.com.letscode.java.excecoes.ExcedeuQuantidadeMaximaLivrosException;
import br.com.letscode.java.factory.PessoaViewFactory;
import br.com.letscode.java.services.BibliotecaService;

import javax.inject.Inject;

public class BibliotecaRealizarEmprestimoView {

    @Inject
    private PessoaViewFactory pessoaViewFactory;

    @Inject
    private BibliotecaService bibliotecaService;

    @Inject
    private Logger logger;


    public void realizarEmprestimo(PessoaEnum pessoaEnum) throws ExcedeuQuantidadeMaximaLivrosException {
        Pessoa professor = this.pessoaViewFactory.create(pessoaEnum).create();
        var emprestimo = new Emprestimo(professor, new Livro[]{new Livro(), new Livro(), new Livro()});
        Biblioteca biblioteca = new Biblioteca();
        System.out.println(this.logger.getTempoFormatado());
        this.bibliotecaService.realizarEmprestimo(emprestimo, biblioteca);
    }


}
