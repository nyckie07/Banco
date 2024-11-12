class Pessoa_Fisica extends Cliente {
    private String cpf;
    private String planoIndividual;

    public Pessoa_Fisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public void escolherPlanoIndividual(String plano) {
        this.planoIndividual = plano;
    }

    @Override
    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Plano Individual: " + (planoIndividual != null ? planoIndividual : "Nenhum plano selecionado"));
    }
}
