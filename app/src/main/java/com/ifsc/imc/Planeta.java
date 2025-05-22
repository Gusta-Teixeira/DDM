package com.ifsc.imc;

import java.io.Serializable;

public class Planeta implements Serializable {
    String nome;
    Integer foto;

    public Planeta(String nome, Integer foto) {
        this.nome = nome;
        this.foto = foto;
    }
}
