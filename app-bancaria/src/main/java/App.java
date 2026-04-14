public class App {
    public static void main(String[] args) {
        Banco bancoPatagonia = Banco.getInstancia();
        Admin adminBancoPatagoniaSucursalCaballito = new Admin(bancoPatagonia.getSucursales().get(0));
        Admin adminBancoPatagoniaSucursalMataderos = new Admin(bancoPatagonia.getSucursales().get(1));
        Admin adminBancoPatagoniaSucursalParquePatricios = new Admin(bancoPatagonia.getSucursales().get(2));

        bancoPatagonia.getServiceTransacciones().transferir(
                bancoPatagonia.buscarCuentaGlobal("matias@gmail.com"),
                bancoPatagonia.buscarCuentaGlobal("axel@gmail.com"),
                50.0);
        adminBancoPatagoniaSucursalCaballito.listarUsuariosConSusBalances();
        adminBancoPatagoniaSucursalMataderos.listarUsuariosConSusBalances();
        adminBancoPatagoniaSucursalParquePatricios.listarUsuariosConSusBalances();


    }
}
