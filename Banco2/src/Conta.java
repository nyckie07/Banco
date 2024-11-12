import java.util.ArrayList;

class Conta {
    private Cliente titular;
    private double saldo;
    private ArrayList<String> extrato;

    public Conta(Cliente titular) {
        this.titular = titular;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        extrato.add("Depósito de R$" + valor);
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            extrato.add("Saque de R$" + valor);
            return true;
        }
        System.out.println("Saldo insuficiente!");
        return false;
    }

    public boolean transferir(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
            extrato.add("Transferência de R$" + valor + " para " + destino.getTitular().getNome());
            destino.extrato.add("Transferência de R$" + valor + " de " + titular.getNome());
            return true;
        }
        return false;
    }

    public void exibirExtrato() {
        System.out.println("Extrato da conta de " + titular.getNome() + ":");
        for (String transacao : extrato) {
            System.out.println(transacao);
        }
        System.out.println("Saldo atual: R$" + saldo);
    }
}
