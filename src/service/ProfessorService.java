public class ProfessorService {

    // --- Métodos ---

    public void solicitarBolsa(Professor professor, Disciplina disciplina, String tipoBolsa) {
        System.out.println("LOGICA DE SERVICO: Prof(a). " + professor.getNome() + 
                           " solicitou uma bolsa " + tipoBolsa + 
                           " para a disciplina " + disciplina.getNome() + ".");
    }

    public void indicarMonitor(Professor professor, Disciplina disciplina, AlunoMonitor monitor) {
        disciplina.setMonitor(monitor);
        monitor.setDisciplina(disciplina);
        monitor.setProfessor(professor);
        System.out.println("LOGICA DE SERVICO: O aluno " + monitor.getNome() + 
                           " foi indicado como monitor de " + disciplina.getNome() + 
                           " pelo(a) Prof(a). " + professor.getNome() + ".");
    }

    public void cadastrarHorarioAtendimento(Disciplina disciplina, String dia, String hora, String local) {
        String novoHorario = dia + " das " + hora + " no local: " + local;
        disciplina.adicionarHorario(novoHorario); // Supondo que Disciplina tenha este método
        System.out.println("LOGICA DE SERVICO: Novo horário de atendimento para " + 
                           disciplina.getNome() + " cadastrado: " + novoHorario);
    }

    public void acompanharMonitorias(Professor professor) {
        System.out.println("\n--- Acompanhamento de Monitorias - Prof(a). " + professor.getNome() + " ---");
        for (Disciplina disc : professor.getDisciplinas()) {
            System.out.println("Disciplina: " + disc.getNome());
            AlunoMonitor monitor = disc.getMonitor();
            if (monitor != null) {
                System.out.println("  - Monitor Designado: " + monitor.getNome());
                System.out.println("  - Horários Cadastrados: " + disc.getHorariosAtendimento().size());
            } else {
                System.out.println("  - Nenhum monitor designado ainda.");
            }
        }
        System.out.println("-----------------------------------------------------");
    }
}
