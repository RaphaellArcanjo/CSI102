package model;
public class AlunoMonitor extends Pessoa {

    private Disciplina disciplina;
    private Professor professor;
    // horarios atendimento é do disciplina


    public AlunoMonitor(String nome) {
        super(nome); // Chama o construtor da classe pai (Pessoa)
    }


    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    @Override
    public void apresentar() {
        System.out.println("Sou o Aluno Monitor: " + this.nome);
    }
}
