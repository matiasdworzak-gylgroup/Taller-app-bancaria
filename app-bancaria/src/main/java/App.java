public class App {
    public static void main(String[] args){
        GestorBancario gestorBancario = new GestorBancario();

        UsuarioCliente user1 = new UsuarioCliente("Jose Marmol", "calle 1", TipoDeCuenta.SUELDO);
        UsuarioCliente user2 = new UsuarioCliente("Juan Jota", "calle 2", TipoDeCuenta.CORRIENTE);gestorBancario.altaUsuario(user2);

        gestorBancario.depositar(user2, 14752.34);
        gestorBancario.altaUsuario(user1);
        gestorBancario.depositar(user1, 5000.50);
        gestorBancario.listarUsuariosConSusBalances();

        gestorBancario.transferir(user2, user1, 5000.00);
        gestorBancario.listarUsuariosConSusBalances();

        gestorBancario.transferir(user2, user1, 10000.00);

        gestorBancario.listarUsuariosConSusBalances();
    }
}
