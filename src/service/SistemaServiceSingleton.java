package service;

import java.util.ArrayList;
import java.util.List;
import model.*;

public class SistemaServiceSingleton {

    private static SistemaServiceSingleton instance;

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<BolsaMonitoria> bolsas = new ArrayList<>();

    private SistemaServiceSingleton() {}

    public static SistemaServiceSingleton getInstance() {
        if (instance == null) {
            instance = new SistemaServiceSingleton();
        }
        return instance;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<BolsaMonitoria> getBolsas() {
        return bolsas;
    }
}