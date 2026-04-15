package repository;

import entity.Banco;
import entity.Sucursal;
import entity.Transaccion;
import entity.UsuarioCliente;

import java.util.ArrayList;

public class UsuarioClienteRepository {
    private Banco bandoDb = Banco.getInstancia();

    public UsuarioCliente buscarUsuarioActivoPorMail(String mail) {
        for (Sucursal suc : bandoDb.getSucursales()) {
            for (UsuarioCliente user : suc.getUsuariosActivos()) {
                if (user.getMail().equals(mail)) return user;
            }
        }
        return null;
    }

    public boolean save(UsuarioCliente user) {
        if (user == null) return false;
        return true;}

    public boolean altaUsuario(UsuarioCliente user, Sucursal suc) {
        if (suc == null || user == null) return false;

        suc.getUsuariosActivos().add(user);
        user.setEstaActivado(true);

        return true;
    }

    public boolean existe(UsuarioCliente user) {
        if (user == null) return false;

        return buscarUsuarioActivoPorMail(user.getMail()) != null;
    }

    public ArrayList<Transaccion> obtenerHistorialDeTransaccionesDelUsuario (UsuarioCliente user){
//        System.out.println("-----------------------------");
//        System.out.println("Historial de transacciones del usuario " + user.getName() + ": \n");
        return user.getHistorialTransaccion();
    }
}
