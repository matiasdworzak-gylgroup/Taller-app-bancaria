public class Transaccion {
    private Long id;
    private UsuarioCliente emisor;
    private UsuarioCliente destinatario;
    private Double monto;
    private boolean transaccionExitosa;

    public Transaccion(UsuarioCliente emisor, UsuarioCliente destinatario, Double monto) {
        this.id = id++;
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.monto = monto;
        this.transaccionExitosa = tieneSaldoParaRealizarLaOperacion(emisor, monto);
    }

    public boolean tieneSaldoParaRealizarLaOperacion(UsuarioCliente cliente1, Double monto){
        if(cliente1.getSaldo()>=monto) {
            return true;}
        return false;
    }
}
