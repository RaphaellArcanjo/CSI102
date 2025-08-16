package model;

public class Aluno extends Pessoa {
    private String matricula;
    private String curso;

    public Aluno(String nome, String email, String cpf, String senha, String matricula, String curso) {
        super(nome, email, cpf, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno [nome=" + getNome() + ", matricula=" + matricula + ", curso=" + curso + "]";
    }
}