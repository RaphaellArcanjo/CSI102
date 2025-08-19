package model;

public class Professor extends Pessoa {
    private String siape;
    private Departamento departamento;

    public Professor(String nome, String email, String cpf, String siape) {
        super(nome, email, cpf);
        this.siape = siape;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Professor [nome=" + getNome() + ", siape=" + siape + "]";
    }
}