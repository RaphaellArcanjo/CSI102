package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.*;

public class SistemaService {

    private static SistemaService instance;

    private SistemaService() {}

    public static SistemaService getInstance() {
        if (instance == null) {
            instance = new SistemaService();
        }
        return instance;
    }

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<BolsaMonitoria> bolsas = new ArrayList<>();


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

    public Professor cadastrarProfessor(String nome, String email, String cpf, String senha, String siape, String siglaDepartamento) throws Exception{
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

    public BolsaMonitoria solicitarBolsa(String codigoDisciplina, String siapeProfessor, String matriculaAluno, String tipoBolsa, String semestre) throws  Exception {
        Optional<Disciplina> disciplina = encontrarDisciplinaPeloCodigo(codigoDisciplina);
        Optional<Professor> professor = encontrarProfessorPeloSiape(siapeProfessor);
        Optional<Aluno> aluno = encontrarAlunoPelaMatricula(matriculaAluno);

        if (disciplina.isPresent() && professor.isPresent() && aluno.isPresent()) {
            BolsaMonitoria bolsa = new BolsaMonitoria(tipoBolsa, semestre, aluno.get(), professor.get(), disciplina.get());
            bolsas.add(bolsa);
            return bolsa;
        }
        throw new Exception("Erro ao solicitar bolsa. Verifique os dados (disciplina, professor ou aluno não encontrado).");
    }

    public void cadastrarHorarioAtendimento(String codigoDisciplina, String horario, String local) throws  Exception {
        Optional<BolsaMonitoria> bolsa = encontrarBolsaPelaDisciplina(codigoDisciplina);
        if (bolsa.isPresent()) {
            bolsa.get().setHorarioAtendimento(horario);
            bolsa.get().setLocal(local);
        } else {
            throw new Exception("Monitoria para a disciplina " + codigoDisciplina + " não encontrada.");
        }
    }

    public Optional<BolsaMonitoria> consultarMonitoria(String matriculaAluno) {
        return encontrarBolsaPeloAluno(matriculaAluno);
    }

    public List<BolsaMonitoria> getBolsas() {
        return bolsas;
    }


    public String gerarRelatorioHorariosPorDisciplina() {
        if (bolsas.isEmpty()) {
            return "Nenhuma monitoria cadastrada.";
        }
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n--- Relatório: Horários por Disciplina ---\n");
        for (BolsaMonitoria bolsa : bolsas) {
            relatorio.append("Disciplina: ").append(bolsa.getDisciplina().getNome())
                    .append(" | Horário: ").append(bolsa.getHorarioAtendimento())
                    .append(" | Local: ").append(bolsa.getLocal()).append("\n");
        }
        relatorio.append("------------------------------------------");
        return relatorio.toString();
    }

    public String gerarRelatorioAlunoProfessorPorDisciplina() {
        if (bolsas.isEmpty()) {
            return "Nenhuma monitoria cadastrada para gerar o relatório.";
        }
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n--- Relatório: Aluno e Professor por Disciplina ---\n");
        for (BolsaMonitoria bolsa : bolsas) {
            relatorio.append("Disciplina: ").append(bolsa.getDisciplina().getNome())
                    .append(" | Aluno Monitor: ").append(bolsa.getAluno().getNome())
                    .append(" | Professor Orientador: ").append(bolsa.getProfessor().getNome())
                    .append("\n");
        }
        relatorio.append("---------------------------------------------------\n");
        return relatorio.toString();
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