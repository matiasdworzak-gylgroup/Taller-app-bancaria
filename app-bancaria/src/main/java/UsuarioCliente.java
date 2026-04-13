import java.util.ArrayList;

public class UsuarioCliente {
    private long id;
    private String name;
    private String direccion;
    private TipoDeCuenta tipoDeCuenta;
    private Double saldo;
    private ArrayList<Transaccion> historialTransaccion;

    public UsuarioCliente(String name, String direccion, TipoDeCuenta tipoDeCuenta, Double saldo) {
        this.id = id++;
        this.name = name;
        this.direccion = direccion;
        this.tipoDeCuenta = tipoDeCuenta;
        this.saldo = 0.0;
        this.historialTransaccion = new ArrayList<>();
    }

    public Long getId(){
        return  id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoDeCuenta getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public void setTipoDeCuenta(TipoDeCuenta tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transaccion> getHistorialTransaccion() {
        return historialTransaccion;
    }

    public void setHistorialTransaccion(ArrayList<Transaccion> historialTransaccion) {
        this.historialTransaccion = historialTransaccion;
    }
}
