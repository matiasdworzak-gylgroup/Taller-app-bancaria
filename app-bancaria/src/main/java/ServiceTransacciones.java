public class ServiceTransacciones {

    public ServiceTransacciones(){

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
                System.out.println("La Transaccion de " + emisor.getName() +"a " + destinatario.getName() + " fallo por saldo insuficiente");
                System.out.println("-----------------------------");
            }
            emisor.agregarTransaccion(transaccionPendiente);
        }}

    public boolean verificadorDeUsuarioActivos(UsuarioCliente user){
        if (!user.getEstaActivado()){
            System.out.println("La Transaccion no puede realizarse porque el usuario " + user.getName() + " tiene la cuenta desactivada.");
            return false;
        }
        return true;
    }
}

