package model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nome;
    private List<Disciplina> listaDisciplinas;

    public Departamento(String nome) {
        this.nome = nome;
        this.listaDisciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina != null && !this.listaDisciplinas.contains(disciplina)) {
            this.listaDisciplinas.add(disciplina);
        }
    }
}
