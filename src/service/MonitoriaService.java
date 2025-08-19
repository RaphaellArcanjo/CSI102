package service;

import model.*;
import java.util.List;
import java.util.Optional;

public class MonitoriaService {

    private final List<Aluno> alunos;
    private final List<Professor> professores;
    private final List<Disciplina> disciplinas;
    private final List<BolsaMonitoria> bolsas;

    public MonitoriaService() {
        SistemaServiceSingleton dados = SistemaServiceSingleton.getInstance();
        this.alunos = dados.getAlunos();
        this.professores = dados.getProfessores();
        this.disciplinas = dados.getDisciplinas();
        this.bolsas = dados.getBolsas();
    }

    public BolsaMonitoria solicitarBolsa(String codigoDisciplina, String siapeProfessor, String matriculaAluno, String tipoBolsa, String semestre) throws Exception {
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

    public void cadastrarHorarioAtendimento(String codigoDisciplina, String horario, String local) throws Exception {
        Optional<BolsaMonitoria> bolsa = encontrarBolsaPelaDisciplina(codigoDisciplina);
        if (bolsa.isPresent()) {
            bolsa.get().setHorarioAtendimento(horario);
            bolsa.get().setLocal(local);
        } else {
            throw new Exception("Monitoria para a disciplina " + codigoDisciplina + " não encontrada.");
        }
    }

    public Optional<BolsaMonitoria> consultarMonitoriaPorAluno(String matriculaAluno) {
        return encontrarBolsaPeloAluno(matriculaAluno);
    }

    private Optional<Aluno> encontrarAlunoPelaMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst();
    }

    private Optional<Professor> encontrarProfessorPeloSiape(String siape) {
        return professores.stream().filter(p -> p.getSiape().equals(siape)).findFirst();
    }

    private Optional<Disciplina> encontrarDisciplinaPeloCodigo(String codigo) {
        return disciplinas.stream().filter(d -> d.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }

    private Optional<BolsaMonitoria> encontrarBolsaPelaDisciplina(String codigo) {
        return bolsas.stream().filter(b -> b.getDisciplina().getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }

    private Optional<BolsaMonitoria> encontrarBolsaPeloAluno(String matricula) {
        return bolsas.stream().filter(b -> b.getAluno().getMatricula().equals(matricula)).findFirst();
    }
}