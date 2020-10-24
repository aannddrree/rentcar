package br.com.rentcar.util;

public enum Status {
    ALUGADO(1,"Alugado"),
    DISPONIVEL(2,"Disponível");

    private int id;
    private String descricao;

    Status(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
}
