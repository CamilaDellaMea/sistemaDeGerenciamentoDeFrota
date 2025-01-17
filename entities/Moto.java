package entities;
public class Moto extends Veiculo{
    private boolean partidaEletrica;


    @Override
    public String toString() {
        String partida = this.getPartidaEletrica() ? "Sim" : "Não";
        return "Veículo: " + this.getMarca()
            + " " + this.getModelo()
            + " " + this.getAno()
            + " - Placa: " + this.getPlaca()
            + " - Part. Elétrica: " + partida;
    }

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }
    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }
    public Moto(String marca, String modelo, int ano, boolean partidaEletrica, String placa) {
        super(marca, modelo, ano, placa);
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public double calcularImposto() {
        return 500.0;
    }
    
    
}