class Pessoa_Juridica extends Cliente {
    private String cnpj;
    private String planoColetivo;

    public Pessoa_Juridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public void escolherPlanoColetivo(String plano) {
        this.planoColetivo = plano;
    }

    @Override
    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Plano Coletivo: " + (planoColetivo != null ? planoColetivo : "Nenhum plano selecionado"));
    }
}