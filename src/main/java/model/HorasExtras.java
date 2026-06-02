package model;

public class HorasExtras extends SalarioDecorator {

    private double horasExtras;

    public HorasExtras(ISalario salario,
            double horasExtras) {

        super(salario);

        this.horasExtras = horasExtras;
    } 

    public double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(double horasExtras) {
        this.horasExtras = horasExtras;
    }


    @Override
    public double calcularsalario(){
        double valorHora =
                salario.getSalariobase() / 200;

        double adicional =
                horasExtras
                * (valorHora * 1.5);

        return salario.calcularsalario()
                + adicional;
    }
}
