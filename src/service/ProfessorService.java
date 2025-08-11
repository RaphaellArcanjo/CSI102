public class ProfessorService extends Pessoa {
    // Atributos
    private Disciplina[] disciplinas; // Substituído
    private int numDisciplinas;       // Contador manual
    private static final int MAX_DISCIPLINAS = 10; // Tamanho máximo fixo

    public ProfessorService(String nome) {
        super(nome);
        // Inicializa o array com o tamanho máximo e o contador com zero
        this.disciplinas = new Disciplina[MAX_DISCIPLINAS];
        this.numDisciplinas = 0;
    }

    // Método para adicionar disciplina, agora com controle de capacidade
    public void adicionarDisciplina(Disciplina disciplina) {
        if (this.numDisciplinas < MAX_DISCIPLINAS) {
            this.disciplinas[this.numDisciplinas] = disciplina;
            this.numDisciplinas++;
            disciplina.setProfessorOrientador(this);
        } else {
            System.out.println("Erro: Limite de disciplinas para o professor " + this.nome + " atingido.");
        }
    }

    // Método de acompanhamento modificado para usar o contador manual
    public void acompanharMonitorias() {
        System.out.println("\n--- Acompanhamento de Monitorias - Prof(a). " + this.nome + " ---");
        
        for (int i = 0; i < this.numDisciplinas; i++) {
            Disciplina disc = this.disciplinas[i];
            System.out.println("Disciplina: " + disc.getNome());
            AlunoMonitor monitor = disc.getMonitor();
            if (monitor != null) {
                System.out.println("  - Monitor Designado: " + monitor.getNome());
            } else {
                System.out.println("  - Nenhum monitor designado ainda.");
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    // --- Outros métodos permanecem iguais ---

    public void solicitarBolsa(Disciplina disciplina, String tipoBolsa, int quantidade) {
        System.out.println("Prof(a). " + this.nome + " está solicitando " + quantidade + " bolsa(s) para " + disciplina.getNome() + "...");
        try {
            PROGRAD prograd = PROGRAD.getInstance();
            prograd.concederBolsa(disciplina.getDepartamento(), quantidade);
        } catch (BolsaException e) {
            System.out.println("!!! FALHA NA SOLICITAÇÃO: " + e.getMessage());
        }
    }
    
    @Override
    public void apresentar() {
        System.out.println("Sou o Professor Orientador: " + this.nome);
    }
    
    public void indicarMonitor(Disciplina d, AlunoMonitor m) { 
        d.setMonitor(m);
        m.setDisciplina(d);
        m.setProfessor(this);
        System.out.println("O aluno " + m.getNome() + " foi indicado como monitor de " + d.getNome() + ".");
    }
}