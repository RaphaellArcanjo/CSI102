public class AlunoMonitorService {


    public void consultarMonitoria(AlunoMonitor aluno) {
        System.out.println("\n--- Consulta de Monitoria para o Aluno: " + aluno.getNome() + " ---");
        
        Disciplina disciplina = aluno.getDisciplina();
        Professor professor = aluno.getProfessor();

        if (disciplina != null && professor != null) {
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Professor Orientador: " + professor.getNome());
            System.out.println("Horários de Atendimento:");

            int numHorarios = disciplina.getNumHorariosCadastrados();
            String[] horarios = disciplina.getHorariosAtendimento();

            if (numHorarios == 0) {
                System.out.println("- Nenhum horário cadastrado.");
            } else {
                for (int i = 0; i < numHorarios; i++) {
                    System.out.println("- " + horarios[i]);
                }
            }
        } else {
            System.out.println("Você ainda não foi designado para nenhuma monitoria.");
        }
        System.out.println("------------------------------------------");
    }

    public void confirmarParticipacao(AlunoMonitor aluno) {
        if (aluno.getDisciplina() != null) {
            System.out.println("LOGICA DE SERVICO: Aluno " + aluno.getNome() + 
                               " confirmou a participação na monitoria de " + 
                               aluno.getDisciplina().getNome() + ".");
        } else {
            System.out.println("Não há monitoria para confirmar.");
        }
    }
}