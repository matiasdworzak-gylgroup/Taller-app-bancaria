import java.util.ArrayList;
import java.util.Arrays;

public final class Banco {
    private static Banco instancia;
    private String nombre;
    private final ArrayList<Sucursal> sucursales;

    private Banco() {
        sucursales = new ArrayList<>();
        inicializarSucursales();
        cargarUsuariosEnSucursales();
    }

    public static Banco getInstancia(){
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }

    private void inicializarSucursales(){
        sucursales.add(new Sucursal("Sucursal Caballito", "Av. Rivadavia 5350", 0.0));
        sucursales.add(new Sucursal("Sucursal Mataderos", "Av. Alberdi 6800", 0.0));
        sucursales.add(new Sucursal("Sucursal Parque Patricios", "Av. Cordoba 2250", 0.0));
    }

    private void cargarUsuariosEnSucursales(){

        UsuarioCliente user1 = new UsuarioCliente("Jose Marmol", "calle 1", TipoDeCuenta.SUELDO);
        UsuarioCliente user2 = new UsuarioCliente("Axel Jota", "calle 2", TipoDeCuenta.CORRIENTE);
        UsuarioCliente user3 = new UsuarioCliente("Sofia Acevedo", "calle 3", TipoDeCuenta.SUELDO);
        UsuarioCliente user4 = new UsuarioCliente("Tomas Benitez", "calle 4", TipoDeCuenta.AHORRO);
        ArrayList<UsuarioCliente> listaUsuariosCaballito = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }
}

//    public void mostrarCuentas() {
//        System.out.println("-----Detalles de las cuentas del banco-----");
//        for (Sucursal sucursal : sucursales) {
//            System.out.println("-----Detalles de las cuentas de la sucursal " + sucursal.getNombre() + "-----");
//            sucursal.mostrarCuentas();
//        }
//    }