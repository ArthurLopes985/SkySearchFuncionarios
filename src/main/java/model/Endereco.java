package model;
 
public class Endereco {
 
    private final int id;
    private final String cep;
    private final String rua;
    private final String bairro;
    private final String cidade;

    private Endereco(EnderecoBuilder builder) {
        this.id = builder.getId();
        this.cep = builder.getCep();
        this.rua = builder.getRua();
        this.bairro = builder.getBairro();
        this.cidade = builder.getCidade();
    }
 
    public int getId() {
        return id;
    }
 
    public String getCep() {
        return cep;
    }
 
    public String getRua() {
        return rua;
    }
 
    public String getBairro() {
        return bairro;
    }
 
    public String getCidade() {
        return cidade;
    }
 
    public static class EnderecoBuilder {
 
        private int id;
        private String cep;
        private String rua;
        private String bairro;
        private String cidade;
 
        public EnderecoBuilder comId(int id) {
            this.id = id;
            return this;
        }
 
        public EnderecoBuilder comCep(String cep) {
            this.cep = cep;
            return this;
        }
 
        public EnderecoBuilder comRua(String rua) {
            this.rua = rua;
            return this;
        }
 
        public EnderecoBuilder comBairro(String bairro) {
            this.bairro = bairro;
            return this;
        }
 
        public EnderecoBuilder comCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }
 
        public Endereco build() {
            return new Endereco(this);
        }
 
        private int getId() {
            return id;
        }
 
        private String getCep() {
            return cep;
        }
 
        private String getRua() {
            return rua;
        }
 
        private String getBairro() {
            return bairro;
        }
 
        private String getCidade() {
            return cidade;
        }
    }
}