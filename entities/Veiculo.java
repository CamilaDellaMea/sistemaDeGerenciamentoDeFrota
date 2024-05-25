package entities;
import java.time.Year;

public abstract class Veiculo {
    
    private String marca;
    private String modelo;
    private int ano;
    private String placa;
    
    //sobrescrever nas classes filhas
    public abstract double calcularImposto();

    @Override
    public String toString() {
        return  "Veiculo: " +this.getMarca()
            + " " + this.getModelo()
            + " " + this.getAno()
            + " - Placa: " + this.getPlaca();
    }

    public Veiculo(){
        this("Tipo ","",1900,"");
    }
    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    public int calcularTempoDeUso() {
        int anoAtual = Year.now().getValue();
        //int tempoUso = anoAtual - this.ano;
        //return tempoUso;
        return this.calcularTempoDeUso(anoAtual);
    }
    int calcularTempoDeUso(int anoBase) {
        int tempoUso = anoBase - this.ano;
        return tempoUso;
    }
    
    //Métodos Getters and Setters
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    void ligar() {
        System.out.println("Veículo Ligado!");
    }
}