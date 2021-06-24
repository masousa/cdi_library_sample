package br.com.letscode.java.services.impl;

import br.com.letscode.java.dominio.*;
import br.com.letscode.java.excecoes.*;
import br.com.letscode.java.services.BibliotecaService;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Named
public class BibliotecaServiceImpl implements BibliotecaService {


    public void realizarEmprestimo(Emprestimo emprestimo, Biblioteca biblioteca) {
        //1 - verificar se é Aluno ou Professor
        //2 - checar se o livro está disponível
        //3 - checar se é possível realizar o empréstimo
        this.livrosEstaoTodosDisponiveis(emprestimo.getLivros());
        String matricula = emprestimo.getPessoa().getMatricula();
        if (emprestimo.getPessoa() instanceof Aluno) {
            boolean existeEmprestimoEmAndamento = this.existeEmprestimoEmAndamento(matricula, biblioteca.getEmprestimos());
            if (existeEmprestimoEmAndamento) {
                throw new AlunoJaTemEmprestrimoEmAndamentoException(matricula);
            }
            var aluno = (Aluno) emprestimo.getPessoa();
            final var suspensoAte = aluno.getSuspensoAte();
            if (suspensoAte != null && suspensoAte.isAfter(LocalDate.now())) {
                throw new AlunoSuspensoException(matricula, suspensoAte);
            }
        } else if (emprestimo.getPessoa() instanceof Professor) {
            int totalLivrosEmprestados = 0;
            for (Emprestimo emp : biblioteca.getEmprestimos()) {
                if (emp.getPessoa().getMatricula().equals(emprestimo.getPessoa().getMatricula())
                        && emp.getDataDevolucao() == null) {
                    totalLivrosEmprestados += emp.getLivros().length;
                }
            }
            if (emprestimo.getLivros().length + totalLivrosEmprestados > Professor.QTD_LIVROS) {
                throw new ProfessorEstourouLimiteLivrosException(totalLivrosEmprestados);
            }
        }
        biblioteca.getEmprestimos().add(emprestimo);
        System.out.println("Empréstimo com sucesso.");
    }

    private void livrosEstaoTodosDisponiveis(Livro[] livros) {
        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                throw new LivroNaoDisponivelException(livro);
            }
        }
    }

    private boolean existeEmprestimoEmAndamento(String matricula, List<Emprestimo> emprestimos) {
        for (Emprestimo emprestimo : emprestimos) {
            final Pessoa pessoaEmprestimo = emprestimo.getPessoa();
            final String pessoaEmprestimoMatricula = pessoaEmprestimo.getMatricula();
            if (emprestimo.getDataDevolucao() == null && pessoaEmprestimoMatricula.equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public void realizarDevolucao(Emprestimo emprestimo, Biblioteca biblioteca) {
        if (!biblioteca.getEmprestimos().contains(emprestimo)) {
            throw new EmprestimoNaoEncontradoException();
        }
        final var dataPrevistaDevolucao = emprestimo.calcularDataPrevistaDevolucao();
        final LocalDate hoje = LocalDate.now().plusDays(20);
        if (hoje.isAfter(dataPrevistaDevolucao) && emprestimo.getPessoa() instanceof Aluno) {
            aplicarSuspensaoNoAluno(emprestimo, dataPrevistaDevolucao, hoje);
        }
        emprestimo.setDataDevolucao(hoje);
        for (Livro livro : emprestimo.getLivros()) {
            livro.setDisponivel(true);
        }
    }

    private void aplicarSuspensaoNoAluno(Emprestimo emprestimo, LocalDate dataPrevistaDevolucao, LocalDate dataEfetivaDevolucao) {
        //Realizando o cast de Pessoa para Aluno, para poder acessar o método "setSuspensoAte"
        Aluno aluno = (Aluno) emprestimo.getPessoa();
        //https://www.baeldung.com/java-date-difference
        final int diasAtraso = Period.between(dataPrevistaDevolucao, dataEfetivaDevolucao).getDays();
        LocalDate suspensoAte = dataEfetivaDevolucao.plusDays(diasAtraso);
        aluno.setSuspensoAte(suspensoAte);
    }
}