package service;

import model.*;
import java.util.List;
import java.util.Optional;


public class CadastroService {
    private final List<Aluno> alunos;
    private final List<Professor> professores;
    private final List<Departamento> departamentos;
    private final List<Disciplina> disciplinas;

    public CadastroService() {
        SistemaServiceSingleton dados = SistemaServiceSingleton.getInstance();
        this.alunos = dados.getAlunos();
        this.professores = dados.getProfessores();
        this.departamentos = dados.getDepartamentos();
        this.disciplinas = dados.getDisciplinas();
    }

    public Departamento cadastrarDepartamento(String nome, String sigla) {
        Departamento depto = new Departamento(nome, sigla);
        departamentos.add(depto);
        return depto;
    }

    public Aluno cadastrarAluno(String nome, String email, String cpf, String senha, String matricula, String curso) {
        Aluno aluno = new Aluno(nome, email, cpf, senha, matricula, curso);
        alunos.add(aluno);
        return aluno;
    }

    public Professor cadastrarProfessor(String nome, String email, String cpf, String senha, String siape, String siglaDepartamento) throws Exception {
        Optional<Departamento> depto = encontrarDepartamentoPelaSigla(siglaDepartamento);
        if (depto.isPresent()) {
            Professor prof = new Professor(nome, email, cpf, senha, siape);
            prof.setDepartamento(depto.get());
            professores.add(prof);
            return prof;
        }
        throw new Exception("Erro: Departamento " + siglaDepartamento + " não encontrado.");
    }

    public Disciplina cadastrarDisciplina(String codigo, String nome, int cargaHoraria, String siglaDepartamento) throws Exception {
        Optional<Departamento> depto = encontrarDepartamentoPelaSigla(siglaDepartamento);
        if (depto.isPresent()) {
            Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria);
            disciplina.setDepartamento(depto.get());
            disciplinas.add(disciplina);
            return disciplina;
        }
        throw new Exception("Erro: Departamento " + siglaDepartamento + " não encontrado.");
    }

    private Optional<Departamento> encontrarDepartamentoPelaSigla(String sigla) {
        return departamentos.stream().filter(d -> d.getSigla().equalsIgnoreCase(sigla)).findFirst();
    }
}
