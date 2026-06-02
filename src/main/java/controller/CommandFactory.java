package controller;

public class CommandFactory {

    private final String PACKAGE_PATH = "controller.";

    public Command getCommand(String acao) {

        try {

            // monta o nome completo da classe
            String caminhoCompleto =
                    PACKAGE_PATH + acao + "Command";

            // carrega a classe dinamicamente
            Class<?> classeCommand =
                    Class.forName(caminhoCompleto);

            // cria objeto da classe
            return (Command) classeCommand
                    .getDeclaredConstructor()
                    .newInstance();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao criar comando: "
                    + e.getMessage());

            return null;
        }
    }
}
