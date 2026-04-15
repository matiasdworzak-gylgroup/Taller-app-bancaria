package service;

import entity.Sucursal;
import entity.Transaccion;
import entity.UsuarioCliente;
import entity.enums.TipoDeCuenta;
import repository.TransaccionRepository;
import repository.UsuarioClienteRepository;

import java.util.ArrayList;

public class UsuarioClienteService {

    private UsuarioClienteRepository usuarioRepo;

    public UsuarioClienteService(UsuarioClienteRepository usuarioRepo){
        this.usuarioRepo = usuarioRepo;

    }


    public boolean altaUsuario(String nombre, String mail, String password, String direccion, TipoDeCuenta tipo, Sucursal sucursalActual) {
        if (nombre != null && mail != null && password != null) {
        UsuarioCliente user = new UsuarioCliente(nombre, mail, password, direccion, tipo);
        return usuarioRepo.altaUsuario(user, sucursalActual);
        }
        return false;
    }

    public UsuarioCliente validarUsuario(String mail, String password){
        UsuarioCliente userBuscado = usuarioRepo.buscarUsuarioActivoPorMail(mail);
        if(userBuscado != null && userBuscado.getPassword().equals(password)){
            return userBuscado;
        }
        return null;
    }



//    public void bajaUsuario(UsuarioCliente user) {
//        if (user.getEstaActivado()) {
//            sucursal.getUsuariosActivos().remove(user);
//            sucursal.getUsuariosDadosDeBaja().add(user);
//            user.setEstaActivado(false);
//            System.out.println("El usuario " + user.getName() + " a sido dado de baja!");
//        }

        public ArrayList<Transaccion> obtenerHistorial(String mail) {
            UsuarioCliente user = usuarioRepo.buscarUsuarioActivoPorMail(mail);
           if(usuarioRepo.existe(user)){
               return user.getHistorialTransaccion();}
           return null;
        }


}

//    public void listarUsuarios() {
//        int i;
//        for (i = 0; i < sucursal.getUsuariosActivos().size(); i++) {
//            System.out.println(sucursal.getUsuariosActivos().get(i).getName());
//        }
//    }
//
//    public void listarUsuariosConSusBalances() {
//        int i;
//        for (i = 0; i < sucursal.getUsuariosActivos().size(); i++) {
//            System.out.println("Nombre: " + sucursal.getUsuariosActivos().get(i).getName() + "\nBalance: $" + sucursal.getUsuariosActivos().get(i).getSaldo());
//            System.out.println("-----------------------------");
//        }
//    }

