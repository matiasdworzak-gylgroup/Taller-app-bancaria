package service;

import entity.Transaccion;
import entity.UsuarioCliente;
import repository.TransaccionRepository;
import repository.UsuarioClienteRepository;

public class TransaccionService {
    private UsuarioClienteRepository usuarioRepo;
    private TransaccionRepository transaccionRepo;

    public TransaccionService(UsuarioClienteRepository usuarioRepo, TransaccionRepository transaccionRepo){
        this.usuarioRepo = usuarioRepo;
        this.transaccionRepo = transaccionRepo;
    }

    public void transferir(UsuarioCliente emisor, UsuarioCliente destinatario, Double monto) {
        if(verificadorDeUsuarioActivos(emisor)&& verificadorDeUsuarioActivos(destinatario)){
            Transaccion transaccionPendiente = new Transaccion(emisor, destinatario, monto);
            if (transaccionPendiente.getTransaccionExitosa()) {
                emisor.restarSaldo(monto);
                destinatario.sumarSaldo(monto);
                System.out.println("El usuario " + emisor.getName() + " transfirio $" + monto + " al usuario " + destinatario.getName());
                System.out.println("-----------------------------");
            } else {
                System.out.println("La Entity.Transaccion de " + emisor.getName() +"a " + destinatario.getName() + " fallo por saldo insuficiente");
                System.out.println("-----------------------------");
            }
            emisor.agregarTransaccion(transaccionPendiente);
        }}

    public boolean verificadorDeUsuarioActivos(UsuarioCliente user){
        if (!user.getEstaActivado()){
            System.out.println("La Entity.Transaccion no puede realizarse porque el usuario " + user.getName() + " tiene la cuenta desactivada.");
            return false;
        }
        return true;
    }

    public boolean depositar(UsuarioCliente user, Double monto) {
        if (user == null || monto == null || monto <= 0) {
            return false;
        }
        if (!usuarioRepo.existe(user)) {
            return false;
        }
        user.setSaldo(user.getSaldo() + monto);
        return usuarioRepo.save(user);
    }

}

