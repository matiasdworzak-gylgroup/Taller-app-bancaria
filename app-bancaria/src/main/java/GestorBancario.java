import java.sql.SQLOutput;
import java.util.ArrayList;

public class GestorBancario {
    private ArrayList<UsuarioCliente> usuariosActivos;
    private ArrayList<UsuarioCliente> usuariosDadosDeBaja;

    public GestorBancario() {
        usuariosActivos = new ArrayList<>();
        usuariosDadosDeBaja = new ArrayList<>();
    }

    public void altaUsuario(UsuarioCliente user) {
        if (user.getName() != null && user.getDireccion() != null && user.getTipoDeCuenta() != null) {
            usuariosActivos.add(user);
            user.setEstaActivado(true);
            System.out.println("El usuario " + user.getName() + " a sido dado de alta!");
        }

    }
    public void bajaUsuario(UsuarioCliente user) {
        if (user.getEstaActivado()) {
            usuariosActivos.remove(user);
            usuariosDadosDeBaja.add(user);
            user.setEstaActivado(false);
            System.out.println("El usuario " + user.getName() + " a sido dado de baja!");
        }

    }



    public void listarUsuarios() {
        int i;
        for (i = 0; i < usuariosActivos.size(); i++) {
            System.out.println(usuariosActivos.get(i).getName());
        }
    }

    public void listarUsuariosConSusBalances() {
        int i;
        for (i = 0; i < usuariosActivos.size(); i++) {
            System.out.println("Nombre: " + usuariosActivos.get(i).getName() + "\nBalance: $" + usuariosActivos.get(i).getSaldo());
            System.out.println("-----------------------------");
        }
    }

    public void depositar(UsuarioCliente user, Double monto) {
        user.setSaldo(user.getSaldo() + monto);
    }

    public void transferir(UsuarioCliente emisor, UsuarioCliente destinatario, Double monto) {
        Transaccion transaccionPendiente = new Transaccion(emisor, destinatario, monto);
        if (transaccionPendiente.getTransaccionExitosa()) {
            emisor.restarSaldo(monto);
            destinatario.sumarSaldo(monto);
        } else {
            System.out.println("La Transaccion fallo por saldo insuficiente");
        }
        emisor.agregarTransaccion(transaccionPendiente);
    }

    public ArrayList<Transaccion> obtenerHistorialDeTransaccionesDelUsuario (UsuarioCliente user){
        return user.getHistorialTransaccion();
    }
}