package model;

public class Disciplina {
    private String nome;
    private Departamento departamento;
    private Professor professor;
    private AlunoMonitor monitor;
    private String horariosAtendimento;

    public Disciplina(String nome, Departamento departamento) {
        this.nome = nome;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Professor getProfessor() {
        return professor;
    }

    public AlunoMonitor getMonitor() {
        return monitor;
    }

    public String getHorariosAtendimento() {
        return horariosAtendimento;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setMonitor(AlunoMonitor monitor) {
        this.monitor = monitor;
    }

    public void setHorariosAtendimento(String horariosAtendimento) {
        this.horariosAtendimento = horariosAtendimento;
    }
}
