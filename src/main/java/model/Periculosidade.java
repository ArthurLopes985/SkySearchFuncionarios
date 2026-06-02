package model;

public class Periculosidade extends SalarioDecorator {

    public Periculosidade(ISalario salario) {
        super(salario);
    }

    @Override
    public double calcularsalario(){
        return salario.calcularsalario() + (salario.getSalariobase() * 0.30);
    }

}
