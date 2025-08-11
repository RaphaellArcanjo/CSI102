public class Professor extends Pessoa {
    // Atributos privados
    private Disciplina[] disciplinas;
    private int numDisciplinas;
    private static final int MAX_DISCIPLINAS = 10;

    // Construtor
    public Professor(String nome) {
        super(nome); // Chama o construtor da classe pai Pessoa
        this.disciplinas = new Disciplina[MAX_DISCIPLINAS];
        this.numDisciplinas = 0;
    }

    // --- Getters e Setters ---
    public Disciplina[] getDisciplinas() {
        return this.disciplinas;
    }

    public int getNumDisciplinas() {
        return this.numDisciplinas;
    }

    // Método para adicionar disciplina (ainda é uma responsabilidade do objeto Professor)
    public void adicionarDisciplina(Disciplina disciplina) {
        if (this.numDisciplinas < MAX_DISCIPLINAS) {
            this.disciplinas[this.numDisciplinas] = disciplina;
            this.numDisciplinas++;
            disciplina.setProfessorOrientador(this); // Mantém a associação
        } else {
            System.out.println("Erro: Limite de disciplinas para o professor " + this.nome + " atingido.");
        }
    }
    
    @Override
    public void apresentar() {
        System.out.println("Sou o Professor: " + this.nome);
    }
}