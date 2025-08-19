package service;

import model.BolsaMonitoria;
import java.util.List;

public class RelatorioService {

    private final List<BolsaMonitoria> bolsas;

    public RelatorioService() {
        this.bolsas = SistemaServiceSingleton.getInstance().getBolsas();
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
        relatorio.append("------------------------------------------\n");
        return relatorio.toString();
    }

    public String gerarRelatorioAlunoProfessorPorDisciplina() {
        if (bolsas.isEmpty()) {
            return "Nenhuma monitoria cadastrada.";
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
}