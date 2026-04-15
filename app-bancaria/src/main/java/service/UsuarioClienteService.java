package service;

import entity.Transaccion;
import entity.UsuarioCliente;
import repository.TransaccionRepository;
import repository.UsuarioClienteRepository;

import java.util.ArrayList;

public class UsuarioClienteService {

    private UsuarioClienteRepository usuarioRepo;

    public UsuarioClienteService(UsuarioClienteRepository usuarioRepo){
        this.usuarioRepo = usuarioRepo;

    }
//    public void altaUsuario(UsuarioCliente user) {
//        if (user.getName() != null && user.getDireccion() != null && user.getTipoDeCuenta() != null) {
//            sucursal.getUsuariosActivos().add(user);
//            user.setEstaActivado(true);
//            System.out.println("El usuario " + user.getName() + " a sido dado de alta!");
//        }
//
//    }
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

