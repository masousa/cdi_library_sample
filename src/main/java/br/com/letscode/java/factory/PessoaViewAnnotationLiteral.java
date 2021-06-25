package br.com.letscode.java.factory;

import br.com.letscode.java.annotation.TipoPessoa;
import br.com.letscode.java.dominio.PessoaEnum;

import javax.enterprise.util.AnnotationLiteral;

public class PessoaViewAnnotationLiteral extends AnnotationLiteral<TipoPessoa> implements TipoPessoa {

    private final PessoaEnum pessoaEnum;

    public PessoaViewAnnotationLiteral(PessoaEnum pessoaEnum) {
        this.pessoaEnum = pessoaEnum;
    }

    @Override
    public PessoaEnum value() {
        return this.pessoaEnum;
    }
}
