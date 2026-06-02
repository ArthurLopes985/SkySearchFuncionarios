package model;
 
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
 
public class RegistroPonto {
 
    private final int id;
    private final int idFuncionario;
    private final LocalDate data;
    private final LocalTime horaEntrada;
    private final LocalTime horaSaida;
 
    private RegistroPonto(RegistroPontoBuilder builder) {
        this.id = builder.id;
        this.idFuncionario = builder.idFuncionario;
        this.data = builder.data;
        this.horaEntrada = builder.horaEntrada;
        this.horaSaida = builder.horaSaida;
    }
 
    public int getId() {
        return id;
    }
 
    public int getIdFuncionario() {
        return idFuncionario;
    }
 
    public LocalDate getData() {
        return data;
    }
 
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
 
    public LocalTime getHoraSaida() {
        return horaSaida;
    }
 
    public double calcularHorasTrabalhadas() {
 
        if (horaEntrada == null || horaSaida == null) {
            return 0.0;
        }
 
        return Duration
                .between(horaEntrada, horaSaida)
                .toMinutes() / 60.0;
    }
 
    public double calcularHorasExtras() {
 
        double horasTrabalhadas = calcularHorasTrabalhadas();
 
        if (horasTrabalhadas > 8) {
            return horasTrabalhadas - 8;
        }
 
        return 0.0;
    }
 
    public String verificarAtraso() {
 
        if (horaEntrada == null) {
            return "Sem registro de entrada";
        }
 
        LocalTime horarioPadrao = LocalTime.of(8, 0);
 
        if (horaEntrada.isAfter(horarioPadrao)) {
 
            long minutosAtraso =
                    Duration.between(horarioPadrao, horaEntrada)
                            .toMinutes();
 
            return "Sim, " + minutosAtraso + " minutos de atraso.";
        }
 
        return "Não";
    }
 
    public double calcularHorasDevendo() {
 
        double horasTrabalhadas = calcularHorasTrabalhadas();
 
        if (horasTrabalhadas < 8) {
            return 8 - horasTrabalhadas;
        }
 
        return 0.0;
    }
 
    public static class RegistroPontoBuilder {
 
        private int id;
        private int idFuncionario;
        private LocalDate data;
        private LocalTime horaEntrada;
        private LocalTime horaSaida;
 
        public RegistroPontoBuilder comId(int id) {
            this.id = id;
            return this;
        }
 
        public RegistroPontoBuilder comFuncionario(int idFuncionario) {
            this.idFuncionario = idFuncionario;
            return this;
        }
 
        public RegistroPontoBuilder comData(LocalDate data) {
            this.data = data;
            return this;
        }
 
        public RegistroPontoBuilder comHoraEntrada(LocalTime horaEntrada) {
            this.horaEntrada = horaEntrada;
            return this;
        }
 
        public RegistroPontoBuilder comHoraSaida(LocalTime horaSaida) {
            this.horaSaida = horaSaida;
            return this;
        }
 
        public RegistroPonto build() {
            return new RegistroPonto(this);
        }
    }
}