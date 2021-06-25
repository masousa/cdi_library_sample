package br.com.letscode.java;

import br.com.letscode.java.dominio.*;
import br.com.letscode.java.excecoes.ExcedeuQuantidadeMaximaLivrosException;
import br.com.letscode.java.excecoes.ProfessorEstourouLimiteLivrosException;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.time.LocalDate;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {

        Aplicacao aplicacao = new Aplicacao();
        //1 - quantidade máxima de livros por empréstimo
//        aplicacao.emprestimoAcimaDoMaximo();
        //2 - o prazo de devolução para empréstimo de aluno é de 10 dias úteis
//        aplicacao.prazoDevolucaoAluno();
        //3 - Aluno não pode realizar mais de um empréstimo
//        aplicacao.variosEmprestimosAluno();
        //4 - Aluno suspenso tentando pegar livro
//        aplicacao.alunoSuspenso();
        //5 - Professor tentando pegar mais livros que o permitido
        //aplicacao.professorLivrosDemais();

        final WeldContainer container = new Weld().initialize();
        final PropriedadesInjetadas propriedadesInjetadas = container.select(PropriedadesInjetadas.class).get();
        //5 - Professor tentando pegar mais livros que o permitido com cdi
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("Escolha a opção \n 1 - devolução \n 2 - Emprestimo \n 3 - Sair");

            opcao = sc.nextInt();
            PessoaEnum enumType;

            switch (opcao) {
                case 1:
                    enumType = definirEnum(sc);
                    aplicacao.realizarDevolucao(propriedadesInjetadas, enumType);
                    break;
                case 2:
                    enumType = definirEnum(sc);
                    aplicacao.professorLivrosDemais(propriedadesInjetadas, enumType);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("1,2 ou 3");
            }
        } while (opcao < 3);
    }

    private static PessoaEnum definirEnum(Scanner sc) {
        System.out.println("Selecione o Tipo de pessoa \n 1 - Professor \n 2 - Aluno");
        int tipoPessoa = sc.nextInt();
        switch (tipoPessoa) {
            case 1:
                return PessoaEnum.PROFESSOR;
            case 2:
                return PessoaEnum.ALUNO;
            default:
                return PessoaEnum.ALUNO;
        }


    }

    /**
     * Tenta instanciar um Empréstimo com a quantidade de Livros acima do máximo permitido
     */
    private void emprestimoAcimaDoMaximo() {
        Pessoa professor = new Professor();
        Livro[] livros = new Livro[5];
        try {
            Emprestimo emprestimo = new Emprestimo(professor, livros);
            System.out.println(emprestimo);
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * O prazo de devolução para empréstimo de aluno é de 10 dias úteis
     */
    private void prazoDevolucaoAluno() {
        Pessoa aluno = new Aluno();
        Livro[] livros = {new Livro()};
        try {
            final var emprestimo = new Emprestimo(aluno, livros);
            final LocalDate dataPrevistaDevolucao = emprestimo.calcularDataPrevistaDevolucao();
            System.out.println(dataPrevistaDevolucao);
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void variosEmprestimosAluno() {
        Pessoa aluno = new Aluno("Jessé", "123", "jesse@letscode.com.br");
        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            Biblioteca biblioteca = new Biblioteca();

            //biblioteca.realizarEmprestimo(emprestimo, biblioteca);
            //biblioteca.realizarEmprestimo(emprestimo, biblioteca);
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void realizarDevolucao() {
        Pessoa aluno = new Aluno("Jessé", "123", "jesse@letscode.com.br");
        Biblioteca biblioteca = new Biblioteca();
        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            //biblioteca.realizarEmprestimo(emprestimo);
            //biblioteca.realizarDevolucao(emprestimo);
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            //biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void professorLivrosDemais() {
        Pessoa professor = new Professor("Jessé", "123", "jesse@letscode.com.br");
        try {
            var emprestimo = new Emprestimo(professor, new Livro[]{new Livro(), new Livro(), new Livro()});
            Biblioteca biblioteca = new Biblioteca();
            //biblioteca.realizarEmprestimo(emprestimo);
            //biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQuantidadeMaximaLivrosException | ProfessorEstourouLimiteLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void professorLivrosDemais(PropriedadesInjetadas propriedadesInjetadas, PessoaEnum pessoaEnum) {

        try {

            propriedadesInjetadas.getBibliotecaView().realizarEmprestimo(pessoaEnum);
            //biblioteca.realizarEmprestimo(emprestimo);

            //biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQuantidadeMaximaLivrosException | ProfessorEstourouLimiteLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void realizarDevolucao(PropriedadesInjetadas propriedadesInjetadas, PessoaEnum pessoaEnum) {

        try {
            propriedadesInjetadas.getBibliotecaRealizarDevolucaoView().realizarDevolucao(pessoaEnum);
            ;
        } catch (ExcedeuQuantidadeMaximaLivrosException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
