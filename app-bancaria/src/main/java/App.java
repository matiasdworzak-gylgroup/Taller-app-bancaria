public class App {
    public static void main(String[] args) {
        Banco bancoPatagonia = Banco.getInstancia();
        Admin adminBancoPatagoniaSucursalCaballito = new Admin(bancoPatagonia.getSucursales().get(0));
        Admin adminBancoPatagoniaSucursalMataderos = new Admin(bancoPatagonia.getSucursales().get(1));
        Admin adminBancoPatagoniaSucursalParquePatricios = new Admin(bancoPatagonia.getSucursales().get(2));

        


    }
}
