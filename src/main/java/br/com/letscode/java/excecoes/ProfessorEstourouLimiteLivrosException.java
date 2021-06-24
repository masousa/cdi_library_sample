package br.com.letscode.java.excecoes;

public class ProfessorEstourouLimiteLivrosException extends RuntimeException {

    public ProfessorEstourouLimiteLivrosException(int totalLivrosEmprestados) {
        super(String.format("Não é possível realizar o novo empréstimo. O professor já possui %d livros emprestados "
            + "atualmente.", totalLivrosEmprestados));
    }
}
