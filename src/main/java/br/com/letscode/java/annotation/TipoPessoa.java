package br.com.letscode.java.annotation;

import br.com.letscode.java.dominio.PessoaEnum;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoPessoa {
    PessoaEnum value();
}
