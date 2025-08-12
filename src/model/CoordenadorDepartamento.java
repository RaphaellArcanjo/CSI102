package model;
import model.Departamento;

public class CoordenadorDepartamento {
    private Departamento departamento;

    public CoordenadorDepartamento(Departamento departamento){
        this.departamento = departamento;
    }

    public void consultarBolsas(){
        int bolsasDisponiveis = this.departamento.getBolsasDisponiveis();
        System.out.println("O departamento " + this.departamento.getNome() + " tem " + bolsasDisponiveis + " bolsas disponíveis.");
    }

    public void distribuirBolsas(Disciplina disciplina, int quantidade) {
        if (this.departamento.getBolsasDisponiveis() >= quantidade) {
            this.departamento.subtrairBolsas(quantidade);
            disciplina.setBolsasAlocadas(quantidade); //tem q colocar esse metodo no disciplinas
            System.out.println(quantidade + " bolsas foram alocadas para a disciplina " + disciplina.getNome() + ".");
        } else {
            System.out.println("Não há bolsas suficientes para distribuir para a disciplina " + disciplina.getNome() + ".");
        }
    }
}
