public class App {
    public static void main(String[] args){
        GestorBancario gestorBancario = new GestorBancario();

        //Creacion de usuarios
        UsuarioCliente user1 = new UsuarioCliente("Jose Marmol", "calle 1", TipoDeCuenta.SUELDO);
        UsuarioCliente user2 = new UsuarioCliente("Axel Jota", "calle 2", TipoDeCuenta.CORRIENTE);
        UsuarioCliente user3 = new UsuarioCliente("Sofia Acevedo", "calle 3", TipoDeCuenta.SUELDO);
        UsuarioCliente user4 = new UsuarioCliente("Tomas Benitez", "calle 4", TipoDeCuenta.AHORRO);

        //Alta usuarios
        gestorBancario.altaUsuario(user1);
        gestorBancario.altaUsuario(user2);
        gestorBancario.altaUsuario(user3);
        gestorBancario.altaUsuario(user4);

        //Depositar en sus cuentas
        gestorBancario.depositar(user2, 14752.34);
        gestorBancario.depositar(user1, 5000.50);
        gestorBancario.depositar(user1, 10000.50);
        gestorBancario.depositar(user3, 40000.00);

        //Listar usuarios
        System.out.println("Balance de usuarios activos: ");
        System.out.println("-----------------------------");
        gestorBancario.listarUsuariosConSusBalances();

        //Transacciones
        gestorBancario.transferir(user2, user1, 5000.00);
        gestorBancario.transferir(user2, user1, 10000.00);
        gestorBancario.transferir(user3, user4, 22000.00);
        gestorBancario.transferir(user4, user2, 22001.00);

        //Dar de baja a un usuario
        System.out.println("Dar de baja el usuario de Axel ");
        gestorBancario.bajaUsuario(user2);
        System.out.println("-----------------------------");
        gestorBancario.transferir(user2, user3, 1500.00);

        //Volver a revisar el balance y si efectivamente esta dadod de baja el usuario
        System.out.println("-----------------------------");
        System.out.println("Balance de usuarios activos: ");
        System.out.println("-----------------------------");
        gestorBancario.listarUsuariosConSusBalances();

        //Historial de transacciones x usuario
        System.out.println(gestorBancario.obtenerHistorialDeTransaccionesDelUsuario(user2));
        System.out.println(gestorBancario.obtenerHistorialDeTransaccionesDelUsuario(user4));
    }
}
