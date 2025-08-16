package model;

public class BolsaMonitoria {
    private String tipo;
    private String semestre;
    private String horarioAtendimento;
    private String local;

    private Aluno aluno;
    private Professor professor;
    private Disciplina disciplina;

    public BolsaMonitoria(String tipo, String semestre, Aluno aluno, Professor professor, Disciplina disciplina) {
        this.tipo = tipo;
        this.semestre = semestre;
        this.aluno = aluno;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "BolsaMonitoria [" +
                "disciplina=" + disciplina.getNome() +
                ", aluno=" + aluno.getNome() +
                ", professor=" + professor.getNome() +
                ", semestre=" + semestre +
                ", tipo=" + tipo +
                ", horario=" + horarioAtendimento +
                ", local=" + local + "]";
    }
}

