package services;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Carro;
import entities.Veiculo;

public class VeiculoService {
    private List<Veiculo> veiculosDB;

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
    }

    public Veiculo save(Veiculo veiculo) throws Exception{
        if(veiculo == null){
            throw new Exception("\nObjeto nulo");
        }
        if(veiculo.getMarca() == null || veiculo.getMarca().isEmpty()){
            throw new Exception("\nNÃO FOI POSSÍVEL CADASTRAR O VEÍCULO \nCampo Marca não pode ser em branco\nPressione Enter para voltar ao Menu Inicial");
        }
        if(veiculo.getModelo() == null || veiculo.getModelo().isEmpty()){
            throw new Exception("\nNÃO FOI POSSÍVEL CADASTRAR O VEÍCULO \nCampo Modelo não pode ser em branco\nPressione Enter para voltar ao Menu Inicial");
        }
        if(veiculo.getAno() <= 0){
            throw new Exception("\nOpção inválida! \nDigite um ano válido: ");
        }
        if (veiculo instanceof Carro) {
            Carro car = (Carro) veiculo;
            if (car.getNumeroPortas() < 2 || car.getNumeroPortas() > 4){
                throw new Exception("\nNÃO FOI POSSÍVEL CADASTRAR O VEÍCULO \nCampo Número de Portas não pode ser em branco\nPressione Enter para voltar ao Menu Inicial");
            }
        }
        if(veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty() || !validarPlaca(veiculo.getPlaca())){
            throw new Exception("\nNÃO FOI POSSÍVEL CADASTRAR O VEÍCULO \nCampo Placa não pode ser em branco\nPressione Enter para voltar ao Menu Inicial");
        }
        if(pesquisarVeiculo(veiculo.getPlaca()) != null){
            throw new Exception("\nJá existe um veículo com essa placa!");
        }
        
        veiculosDB.add(veiculo);
        return veiculo;
    }

    public Veiculo pesquisarVeiculo(String placa){
        for (Veiculo veiculo : veiculosDB){
            if(veiculo.getPlaca().equals(placa)){
                return veiculo;
            }
        }
        return null;
    }

    public void remove(String placa) throws Exception{
        Veiculo veiculo = pesquisarVeiculo(placa);
        if(veiculo == null){
            throw new Exception("\nVeículo não encontrado com a placa informada");
        }
        veiculosDB.remove(veiculo);
    }

    public boolean validarPlaca(String placa) {
        String regex = "[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(placa);

        return matcher.matches();
    }
}