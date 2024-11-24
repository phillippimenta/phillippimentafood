package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception.CpfInvalidoException;

import java.util.Objects;

public class CPF {

    private final String numero;

    public CPF(String numero) {
        String valorSemMascara = numero.replaceAll("\\D", "");
        if (!ehValido(valorSemMascara)) {
            throw new CpfInvalidoException();
        }
        this.numero = valorSemMascara;
    }

    public String getNumero() {
        return this.numero;
    }

    private boolean ehValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }
        return ehCPFValido(cpf);
    }

    private boolean ehCPFValido(String cpf) {
        int[] cpfArray = cpf.chars().map(Character::getNumericValue).toArray();
        // Calcula o primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += cpfArray[i] * (10 - i);
        }
        int primeiroDigito = (soma1 * 10) % 11;
        if (primeiroDigito == 10) primeiroDigito = 0;
        // Calcula o segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += cpfArray[i] * (11 - i);
        }
        int segundoDigito = (soma2 * 10) % 11;
        if (segundoDigito == 10) segundoDigito = 0;
        return primeiroDigito == cpfArray[9] && segundoDigito == cpfArray[10];
    }

    public String getNumeroFormatado() {
        return this.numero.substring(0, 3) + "." +
                this.numero.substring(3, 6) + "." +
                this.numero.substring(6, 9) + "-" +
                this.numero.substring(9, 11);
    }

    @Override
    public String toString() {
        return this.numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(numero, cpf.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    public static void main(String[] args) {
        try {
            CPF cpf = new CPF("12345678909");
            System.out.println("CPF Válido: " + cpf);
            CPF cpf2 = new CPF("001.533.220-98");
            System.out.println("CPF Válido: " + cpf2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

