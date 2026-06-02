package model;

public abstract class SalarioDecorator implements ISalario {

    protected ISalario salario;

    public SalarioDecorator(ISalario salario){
        this.salario = salario;
    }

    @Override
    public double calcularsalario(){
        return salario.calcularsalario();
    }

    @Override
    public double getSalariobase(){
        return salario.getSalariobase();
    }
}
