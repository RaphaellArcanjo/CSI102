package model;

public class Professor extends Pessoa {
    private Disciplina[] disciplinas;
    private int numDisciplinas;
    private static final int MAX_DISCIPLINAS = 10;

    public Professor(String nome) {
        super(nome); // Chama o construtor da classe pai Pessoa
        this.disciplinas = new Disciplina[MAX_DISCIPLINAS];
        this.numDisciplinas = 0;
    }

    public Disciplina[] getDisciplinas() {
        return this.disciplinas;
    }

    public int getNumDisciplinas() {
        return this.numDisciplinas;
    }

    
    @Override
    public void apresentar() {
        System.out.println("Sou o Professor: " + this.nome);
    }
}
