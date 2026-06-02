package model;

public class SalarioBase implements ISalario {

    private double salariobase;

    public SalarioBase(double salarioBase) {
        this.salariobase = salarioBase;
    }

    @Override
    public double calcularsalario(){
        return salariobase;
    }

    @Override
    public double getSalariobase(){
        return salariobase;
    }
}
