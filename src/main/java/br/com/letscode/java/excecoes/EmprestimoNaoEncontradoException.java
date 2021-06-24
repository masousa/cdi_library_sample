package br.com.letscode.java.excecoes;

public class EmprestimoNaoEncontradoException extends RuntimeException {

    public EmprestimoNaoEncontradoException() {
        super("Empréstimo não encontrado!");
    }
}
