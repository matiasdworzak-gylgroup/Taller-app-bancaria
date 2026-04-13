import java.util.UUID;

public class Transaccion {
    private String id;
    private UsuarioCliente emisor;
    private UsuarioCliente destinatario;
    private Double monto;

    public boolean getTransaccionExitosa() {
        return transaccionExitosa;
    }

    public void setTransaccionExitosa(boolean transaccionExitosa) {
        this.transaccionExitosa = transaccionExitosa;
    }

    private boolean transaccionExitosa;

    public Transaccion(UsuarioCliente emisor, UsuarioCliente destinatario, Double monto) {
        this.id = UUID.randomUUID().toString();
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
