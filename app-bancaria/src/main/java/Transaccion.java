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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public UsuarioCliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioCliente destinatario) {
        this.destinatario = destinatario;
    }

    public UsuarioCliente getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioCliente emisor) {
        this.emisor = emisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n {" +
                "id='" + id + '\'' +
                ", emisor=" + emisor.getName() +
                ", destinatario=" + destinatario.getName() +
                ", monto=" + this.getMonto()+
                ", transaccionExitosa=" + this.getTransaccionExitosa() +
                '}';
    }
}
