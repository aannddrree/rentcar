package br.com.rentcar.util;

public enum Fuel {

    ETANOL(1,"Etanol"),
    GASOLINA(2,"Gasolina"),
    FLEX(3,"Flex"),
    GNV(4,"GNV");

    private int id;
    private String descricao;

    Fuel(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }
}
