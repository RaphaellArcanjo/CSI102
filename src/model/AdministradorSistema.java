package model;

import java.util.ArrayList;
import java.util.List;

public class AdministradorSistema {

    private List<Departamento> departamentos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<AlunoMonitor> alunos;

    public AdministradorSistema() {
        departamentos = new ArrayList<>();
        disciplinas = new ArrayList<>();
        professores = new ArrayList<>();
        alunos = new ArrayList<>();
    }

    public void cadastrarDepartamento(Departamento dep) {
        this.departamentos.add(dep);
        System.out.println("LOG: Departamento '" + dep.getNome() + "' cadastrado.");
    }

    public void cadastrarDisciplina(Disciplina disc) {
        this.disciplinas.add(disc);
        if (disc.getDepartamento() != null) {
            disc.getDepartamento().adicionarDisciplina(disc);
        }
        System.out.println("LOG: Disciplina '" + disc.getNome() + "' cadastrada.");
    }

    public void cadastrarProfessor(Professor prof) {
        this.professores.add(prof);
        System.out.println("LOG: Professor '" + prof.getNome() + "' cadastrado.");
    }

    public void cadastrarAluno(AlunoMonitor aluno) {
        this.alunos.add(aluno);
        System.out.println("LOG: Aluno Monitor '" + aluno.getNome() + "' cadastrado.");
    }

    public void emitirRelatorioHorarios() {
        System.out.println("\n--- Relatório de Horários de Atendimento ---");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (Disciplina disc : disciplinas) {
            System.out.println("Disciplina: " + disc.getNome());
            System.out.println("  - Horários: " + (disc.getHorariosAtendimento() != null ? disc.getHorariosAtendimento() : "Não definido"));
        }
        System.out.println("------------------------------------------\n");
    }

    public void emitirRelatorioDisciplinasPorDepartamento() {
        System.out.println("\n--- Relatório de Disciplinas por Departamento ---");
        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado.");
            return;
        }
        for (Departamento dep : departamentos) {
            System.out.println("Departamento: " + dep.getNome());
            if (dep.getListaDisciplinas().isEmpty()) {
                System.out.println("  - Nenhuma disciplina associada.");
            } else {
                for (Disciplina disc : dep.getListaDisciplinas()) {
                    System.out.println("  - " + disc.getNome());
                }
            }
        }
        System.out.println("------------------------------------------------\n");
    }

    public void emitirRelatorioMonitoresProfessores() {
        System.out.println("\n--- Relatório de Monitores e Professores ---");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (Disciplina disc : disciplinas) {
            String nomeProf = disc.getProfessor() != null ? disc.getProfessor().getNome() : "Não atribuído";
            String nomeMonitor = disc.getMonitor() != null ? disc.getMonitor().getNome() : "Não atribuído";

            System.out.println("Disciplina: " + disc.getNome());
            System.out.println("  - Professor Orientador: " + nomeProf);
            System.out.println("  - Aluno Monitor: " + nomeMonitor);
        }
        System.out.println("--------------------------------------------\n");
    }

    public List<Departamento> getDepartamentos() {
        // Retorna uma CÓPIA, não a lista original.
        return new ArrayList<>(this.departamentos);
    }
}
