import java.util.ArrayList;

public class Admin {
    private Sucursal sucursal;

    public Admin(Sucursal sucursal) {
       this.sucursal = sucursal;
    }

    public void altaUsuario(UsuarioCliente user) {
        if (user.getName() != null && user.getDireccion() != null && user.getTipoDeCuenta() != null) {
            sucursal.getUsuariosActivos().add(user);
            user.setEstaActivado(true);
            System.out.println("El usuario " + user.getName() + " a sido dado de alta!");
        }

    }
    public void bajaUsuario(UsuarioCliente user) {
        if (user.getEstaActivado()) {
            sucursal.getUsuariosActivos().remove(user);
            sucursal.getUsuariosDadosDeBaja().add(user);
            user.setEstaActivado(false);
            System.out.println("El usuario " + user.getName() + " a sido dado de baja!");
        }

    }



    public void listarUsuarios() {
        int i;
        for (i = 0; i < sucursal.getUsuariosActivos().size(); i++) {
            System.out.println(sucursal.getUsuariosActivos().get(i).getName());
        }
    }

    public void listarUsuariosConSusBalances() {
        int i;
        for (i = 0; i < sucursal.getUsuariosActivos().size(); i++) {
            System.out.println("Nombre: " + sucursal.getUsuariosActivos().get(i).getName() + "\nBalance: $" + sucursal.getUsuariosActivos().get(i).getSaldo());
            System.out.println("-----------------------------");
        }
    }

    public void depositar(UsuarioCliente user, Double monto) {
        user.setSaldo(user.getSaldo() + monto);
    }



    public ArrayList<Transaccion> obtenerHistorialDeTransaccionesDelUsuario (UsuarioCliente user){
        System.out.println("-----------------------------");
        System.out.println("Historial de transacciones del usuario " + user.getName() + ": \n");
        return user.getHistorialTransaccion();
    }
}