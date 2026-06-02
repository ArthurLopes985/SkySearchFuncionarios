package model;

public class HorasDevendo extends SalarioDecorator {

    private double horasDevendo;

    public HorasDevendo(ISalario salario,
            double horasDevendo) {

        super(salario);

        this.horasDevendo = horasDevendo;
    }
    

    public double getHorasDevendo() {
        return horasDevendo;
    }

    public void setHorasDevendo(double horasDevendo) {
        this.horasDevendo = horasDevendo;
    }


    @Override
    public double calcularsalario() {

        double valorHora =
                salario.getSalariobase() / 200;

        double desconto =
                horasDevendo * valorHora;

        return salario.calcularsalario()
                - desconto;
    }
}
