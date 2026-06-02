package model;

public class Noturno extends SalarioDecorator {
    
    public Noturno(ISalario salario) {

        super(salario);

    } 

    @Override
    public double calcularsalario(){

        return salario.calcularsalario()
                + (salario.getSalariobase() * 0.20);
    }
}
