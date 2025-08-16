package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.*;

public class SistemaService {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<BolsaMonitoria> bolsas = new ArrayList<>();


    public Departamento cadastrarDepartamento(String nome, String sigla) {
        Departamento depto = new Departamento(nome, sigla);
        departamentos.add(depto);
        System.out.println("Departamento cadastrado: " + depto.getNome());
        return depto;
    }

    public Aluno cadastrarAluno(String nome, String email, String cpf, String senha, String matricula, String curso) {
        Aluno aluno = new Aluno(nome, email, cpf, senha, matricula, curso);
        alunos.add(aluno);
        System.out.println("Aluno cadastrado: " + aluno.getNome());
        return aluno;
    }

    public Professor cadastrarProfessor(String nome, String email, String cpf, String senha, String siape, String siglaDepartamento) {
        Optional<Departamento> depto = encontrarDepartamentoPelaSigla(siglaDepartamento);
        if (depto.isPresent()) {
            Professor prof = new Professor(nome, email, cpf, senha, siape);
            prof.setDepartamento(depto.get());
            professores.add(prof);
            System.out.println("Professor cadastrado: " + prof.getNome() + " no depto " + depto.get().getSigla());
            return prof;
        }
        System.out.println("Erro: Departamento " + siglaDepartamento + " não encontrado.");
        return null;
    }

    public Disciplina cadastrarDisciplina(String codigo, String nome, int cargaHoraria, String siglaDepartamento) {
        Optional<Departamento> depto = encontrarDepartamentoPelaSigla(siglaDepartamento);
        if (depto.isPresent()) {
            Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria);
            disciplina.setDepartamento(depto.get());
            disciplinas.add(disciplina);
            System.out.println("Disciplina cadastrada: " + disciplina.getNome());
            return disciplina;
        }
        System.out.println("Erro: Departamento " + siglaDepartamento + " não encontrado.");
        return null;
    }

    public BolsaMonitoria solicitarBolsa(String codigoDisciplina, String siapeProfessor, String matriculaAluno, String tipoBolsa, String semestre) {
        Optional<Disciplina> disciplina = encontrarDisciplinaPeloCodigo(codigoDisciplina);
        Optional<Professor> professor = encontrarProfessorPeloSiape(siapeProfessor);
        Optional<Aluno> aluno = encontrarAlunoPelaMatricula(matriculaAluno);

        if (disciplina.isPresent() && professor.isPresent() && aluno.isPresent()) {
            BolsaMonitoria bolsa = new BolsaMonitoria(tipoBolsa, semestre, aluno.get(), professor.get(), disciplina.get());
            bolsas.add(bolsa);
            System.out.println("Bolsa (" + tipoBolsa + ") para " + disciplina.get().getNome() + " alocada para o aluno " + aluno.get().getNome());
            return bolsa;
        }
        System.out.println("Erro ao solicitar bolsa. Verifique os dados.");
        return null;
    }

    public void cadastrarHorarioAtendimento(String codigoDisciplina, String horario, String local) {
        Optional<BolsaMonitoria> bolsa = encontrarBolsaPelaDisciplina(codigoDisciplina);
        if (bolsa.isPresent()) {
            bolsa.get().setHorarioAtendimento(horario);
            bolsa.get().setLocal(local);
            System.out.println("Horário de atendimento de " + codigoDisciplina + " definido para: " + horario + " no local: " + local);
        } else {
            System.out.println("Monitoria para a disciplina " + codigoDisciplina + " não encontrada.");
        }
    }

    public void consultarMonitoria(String matriculaAluno) {
        Optional<BolsaMonitoria> bolsa = encontrarBolsaPeloAluno(matriculaAluno);
        if (bolsa.isPresent()) {
            System.out.println("\n--- Informações da sua Monitoria ---");
            System.out.println(bolsa.get());
            System.out.println("------------------------------------");
        } else {
            System.out.println("Você não está alocado em nenhuma monitoria.");
        }
    }


    public void emitirRelatorioHorariosPorDisciplina() {
        System.out.println("\n--- Relatório: Horários por Disciplina ---");
        if(bolsas.isEmpty()){
            System.out.println("Nenhuma monitoria cadastrada.");
            return;
        }
        for (BolsaMonitoria bolsa : bolsas) {
            System.out.println("Disciplina: " + bolsa.getDisciplina().getNome() + " | Horário: " + bolsa.getHorarioAtendimento() + " | Local: " + bolsa.getLocal());
        }
        System.out.println("------------------------------------------");
    }

    public void emitirRelatorioAlunoProfessorPorDisciplina() {
        System.out.println("\n--- Relatório: Aluno e Professor por Disciplina ---");
        if(bolsas.isEmpty()){
            System.out.println("Nenhuma monitoria cadastrada.");
            return;
        }
        for (BolsaMonitoria bolsa : bolsas) {
            System.out.println(
                    "Disciplina: " + bolsa.getDisciplina().getNome() +
                            " | Aluno Monitor: " + bolsa.getAluno().getNome() +
                            " | Professor Orientador: " + bolsa.getProfessor().getNome()
            );
        }
        System.out.println("---------------------------------------------------");
    }

    private Optional<Departamento> encontrarDepartamentoPelaSigla(String sigla) {
        return departamentos.stream().filter(d -> d.getSigla().equalsIgnoreCase(sigla)).findFirst();
    }

    private Optional<Disciplina> encontrarDisciplinaPeloCodigo(String codigo) {
        return disciplinas.stream().filter(d -> d.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }

    private Optional<Professor> encontrarProfessorPeloSiape(String siape) {
        return professores.stream().filter(p -> p.getSiape().equals(siape)).findFirst();
    }

    private Optional<Aluno> encontrarAlunoPelaMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst();
    }

    private Optional<BolsaMonitoria> encontrarBolsaPeloAluno(String matricula) {
        return bolsas.stream().filter(b -> b.getAluno().getMatricula().equals(matricula)).findFirst();
    }

    private Optional<BolsaMonitoria> encontrarBolsaPelaDisciplina(String codigo) {
        return bolsas.stream().filter(b -> b.getDisciplina().getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }
}