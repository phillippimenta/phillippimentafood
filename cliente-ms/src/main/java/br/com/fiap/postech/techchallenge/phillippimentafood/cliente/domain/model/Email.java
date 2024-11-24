package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private final String endereco;
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email(String endereco) {
        if (!ehValido(endereco)) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    private boolean ehValido(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public String toString() {
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(endereco, email.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(endereco);
    }

    public static void main(String[] args) {
        try {
            Email email = new Email("exemplo@dominio.com");
            System.out.println("E-mail válido: " + email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

