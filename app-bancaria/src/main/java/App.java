public class App {
    public static void main(String[] args){
        GestorBancario gestorBancario = new GestorBancario();

        UsuarioCliente user1 = new UsuarioCliente("Jose Marmol", "calle 1", TipoDeCuenta.SUELDO);
        gestorBancario.altaUsuario(user1);
        gestorBancario.listarUsuarios();
    }
}
