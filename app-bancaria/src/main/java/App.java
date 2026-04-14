public class App {
    public static void main(String[] args){
        Banco bancoPatagonia = Banco.getInstancia();
        Admin adminBancoPatagoniaSucursalCaballito = new Admin(bancoPatagonia.getSucursales().getFirst());



        //Creacion de usuarios
        UsuarioCliente user1 = new UsuarioCliente("Jose Marmol", "calle 1", TipoDeCuenta.SUELDO);
        UsuarioCliente user2 = new UsuarioCliente("Axel Jota", "calle 2", TipoDeCuenta.CORRIENTE);
        UsuarioCliente user3 = new UsuarioCliente("Sofia Acevedo", "calle 3", TipoDeCuenta.SUELDO);
        UsuarioCliente user4 = new UsuarioCliente("Tomas Benitez", "calle 4", TipoDeCuenta.AHORRO);

        //Alta usuarios
        adminBancoPatagoniaSucursalCaballito.altaUsuario(user1);
        adminBancoPatagoniaSucursalCaballito.altaUsuario(user2);
        adminBancoPatagoniaSucursalCaballito.altaUsuario(user3);
        adminBancoPatagoniaSucursalCaballito.altaUsuario(user4);

        //Depositar en sus cuentas
        adminBancoPatagoniaSucursalCaballito.depositar(user2, 14752.34);
        adminBancoPatagoniaSucursalCaballito.depositar(user1, 5000.50);
        adminBancoPatagoniaSucursalCaballito.depositar(user1, 10000.50);
        adminBancoPatagoniaSucursalCaballito.depositar(user3, 40000.00);

        //Listar usuarios
        System.out.println("Balance de usuarios activos: ");
        System.out.println("-----------------------------");
        adminBancoPatagoniaSucursalCaballito.listarUsuariosConSusBalances();

        //Transacciones
        adminBancoPatagoniaSucursalCaballito.transferir(user2, user1, 5000.00);
        adminBancoPatagoniaSucursalCaballito.transferir(user2, user1, 10000.00);
        adminBancoPatagoniaSucursalCaballito.transferir(user3, user4, 22000.00);
        adminBancoPatagoniaSucursalCaballito.transferir(user4, user2, 22001.00);

        //Dar de baja a un usuario
        System.out.println("Dar de baja el usuario de Axel ");
        adminBancoPatagoniaSucursalCaballito.bajaUsuario(user2);
        System.out.println("-----------------------------");
        adminBancoPatagoniaSucursalCaballito.transferir(user2, user3, 1500.00);

        //Volver a revisar el balance y si efectivamente esta dadod de baja el usuario
        System.out.println("-----------------------------");
        System.out.println("Balance de usuarios activos: ");
        System.out.println("-----------------------------");
        adminBancoPatagoniaSucursalCaballito.listarUsuariosConSusBalances();

        //Historial de transacciones x usuario
        System.out.println(adminBancoPatagoniaSucursalCaballito.obtenerHistorialDeTransaccionesDelUsuario(user2));
        System.out.println(adminBancoPatagoniaSucursalCaballito.obtenerHistorialDeTransaccionesDelUsuario(user4));
    }
}
