package interfaz;

import entity.Sucursal;
import entity.Usuario;
import entity.enums.TipoDeCuenta;
import service.SucursalService;
import service.TransaccionService;
import service.UsuarioClienteService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private UsuarioClienteService userService;
    private TransaccionService transService;
    private SucursalService sucService;
    private Sucursal sucursalActual;
    private Usuario sesionActiva;

    public Menu(UsuarioClienteService userService, TransaccionService transService, SucursalService sucService) {

        this.userService = userService;
        this.transService = transService;
        this.sucService = sucService;
        this.sucursalActual = null;
        this.sesionActiva = null;
        teclado = new Scanner(System.in);
    }


    public void mostrarMenu() {
        boolean correr = true;
        while (correr) {
            System.out.println("\n-----Bienvenido a nuestro banco-----\n");

            ArrayList<Sucursal> listaSucursales = sucService.getTodasLasSucursales();
            mostrarLista("Elija la opcion deseada", listaSucursales);
            int opcion = teclado.nextInt();
            if (opcion > 0 && opcion <= listaSucursales.size()) {
                this.sucursalActual = listaSucursales.get(opcion - 1);
                System.out.println("Entrando a sucursal: " + sucursalActual.getNombre());
                correr = false;
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
                correr = false;
            } else {
                System.out.println("Opción inválida.");
            }
        }
        while (sucursalActual != null) {
            if (sesionActiva == null) {
                // 1. Nadie logueado: Registro o Login
                menuInvitado();
            } else if (sesionActiva.isAdmin()) {
                // 2. Es Admin: Menú con superpoderes
                menuAdmin();
            } else {
                // 3. Es Cliente: Menú de operaciones bancarias
                menuCliente();
            }
        }


        while (sucursalActual != null) {
            System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Registrar cuenta
                    2) Iniciar sesión
                    3) Depositar dinero
                    4) Retirar dinero
                    5) Realizar una transferencia
                    6) Mostrar datos de la cuenta
                    7) Eliminar cuenta
                    8) Cerrar sesión
                    9) Salir de la sucursal
                    """);
            if (sesionActiva != null && sesionActiva.isAdmin()) {
                System.out.println("""
                        Acciones de administrador:
                        10) Mostrar datos de esta sucursal
                        11) Mostrar datos de otra sucursal
                        12) Mostrar datos del banco
                        13) Crear sucursal
                        14) Eliminar una cuenta
                        """);
            }

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;

                case 2:
                    if (sesionActiva == null) {
                        System.out.println("Para iniciar sesion ingrese su mail y constrasenia");
                        System.out.println("Mail:");
                        String mail = teclado.nextLine();
                        System.out.println("Password:");
                        String password = teclado.nextLine();

                        if (userService.validarUsuario(mail, password) != null){
                            sesionActiva = userService.validarUsuario(mail, password);
                        } else {
                            System.out.println("El mail o el password no coinciden con un usuario en esta sucursal");
                            System.out.println("Vas a volver al menu principal");
                        }
                    } else {
                        System.out.println("\nYa hay una sesión activa\n");
                    }
                    break;
                case 3:
                    if (sesionActiva != null) {
                        // procesarDeposito();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 4:
                    if (sesionActiva != null) {
                        // procesarRetiro();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 5:
                    if (sesionActiva != null) {
                        //  procesarTransferencia();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 6:
                    if (sesionActiva != null) {
                        System.out.println(sesionActiva);
                        ;
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 7:
                    if (sesionActiva != null) {
                        //  procesarEliminacion();
                    } else {
                        System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                    }
                    break;
                case 8:
                    if (sesionActiva != null) {
                        sesionActiva = null;
                        System.out.println("\nSesión cerrada con éxito\n");
                    } else {
                        System.out.println("\nNo hay una sesión activa\n");
                    }
                    break;
                case 9:
                    // sucursal = null;
                    sesionActiva = null;
                    break;
                case 10:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //  sucursal.mostrarCuentas();
                    }
                    break;
                case 11:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        System.out.println("\nIngrese el nombre de la sucursal\n");
                        // banco.mostrarSucursales();
                        String sucursalBuscada = teclado.nextLine();
                        //Sucursal otraSucursal = banco.buscarSucursal(sucursalBuscada);
                        // otraSucursal.mostrarCuentas();
                    }
                    break;
                case 12:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //   banco.mostrarCuentas();
                    }
                    break;
                case 13:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        System.out.println("Ingrese el nombre de la nueva sucursal");
                        String nombreNuevaSucursal = teclado.nextLine();
                        //  banco.crearSucursal(nombreNuevaSucursal);
                    }
                    break;
                case 14:
                    if (sesionActiva == null || !sesionActiva.isAdmin()) {
                        System.out.println("\nOpción inválida\n");
                        break;
                    } else {
                        //   procesarEliminacionAdmin();
                    }
                    break;
                default:
                    System.out.println("\nOpción inválida\n");
            }
        }
    }

    private void registrarUsuario(){
        System.out.println("Bienvenido al registro de usuarios.");
        System.out.println("Introduzca su nombre completo:");
        String nombre = teclado.nextLine();
        System.out.println("Introduzca su mail:");
        String mail = teclado.nextLine();
        System.out.println("Introduzca su password:");
        String password = teclado.nextLine();
        System.out.println("Introduzca su direccion:");
        String direccion = teclado.nextLine();

        mostrarOpcionesDeCuenta();
        int opcionTipoDeCuenta;
        do {
            System.out.println("Elija tipo de cuenta (1, 2 o 3):");
            opcionTipoDeCuenta = teclado.nextInt();
        } while (opcionTipoDeCuenta < 1 || opcionTipoDeCuenta > 3);

        TipoDeCuenta tipo = elegirTipoDeCuenta(opcionTipoDeCuenta);

        if(userService.altaUsuario(nombre,mail,password,direccion, tipo, sucursalActual)){
            System.out.println("El usuario " + nombre + " a sido dado de alta!");
        } else{
            System.out.println("Hubo un error, por favor volver a hacer el registro de usuario");
        }
    }
    private <T> void mostrarLista(String titulo, ArrayList<T> lista) {
        System.out.println("=== " + titulo + " ===");
        if (lista.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ") " + lista.get(i));
            }
        }
        System.out.println("0) Presione 0 para salir.");
        System.out.println("-------------------------");
    }

    public void mostrarOpcionesDeCuenta() {
        System.out.println("Seleccione el tipo de cuenta:");
        TipoDeCuenta[] opciones = TipoDeCuenta.values();

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ") " + opciones[i]);
        }
    }

    private TipoDeCuenta elegirTipoDeCuenta(int opcion) {
        return switch (opcion) {
            case 1 -> TipoDeCuenta.CORRIENTE;
            case 2 -> TipoDeCuenta.AHORRO;
            case 3 -> TipoDeCuenta.SUELDO;
            default -> {
                System.out.println("Opción no válida, se asignó AHORRO por defecto.");
                yield TipoDeCuenta.AHORRO;
            }
        };
    }


        private void menuInvitado() {
            System.out.println("""
        --- SUCURSAL: """ + sucursalActual.getNombre() + """
        
         1) Registrar usuario
        2) Iniciar sesión Usuario
        3) Iniciar sesión Admin
        0) Salir de la sucursal
        """);
            int op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1 -> registrarUsuario();
                case 2, 3 -> iniciarSesion(); // El login puede ser el mismo, el objeto dirá qué es
                case 0 -> sucursalActual = null;
                default -> System.out.println("Opción inválida.");
            }
        }


    private void iniciarSesion() {
        System.out.println("Para iniciar sesion ingrese su mail y constrasenia");
        System.out.println("Mail:");
        String mail = teclado.nextLine();
        System.out.println("Password:");
        String password = teclado.nextLine();

        if (userService.validarUsuario(mail, password) != null){
            sesionActiva = userService.validarUsuario(mail, password);
        } else {
            System.out.println("El mail o el password no coinciden con un usuario en esta sucursal");
            System.out.println("Vas a volver al menu principal");
        }
    }
    private void iniciarSesionAdmin() {
        System.out.println("Para iniciar sesion su user y password");
        System.out.println("User:");
        String mail = teclado.nextLine();
        System.out.println("Password:");
        String password = teclado.nextLine();

        if (userService.validarUsuario(mail, password) != null){
            sesionActiva = userService.validarUsuario(mail, password);
        } else {
            System.out.println("El mail o el password no coinciden con un usuario en esta sucursal");
            System.out.println("Vas a volver al menu principal");
        }
    }

    private void menuCliente(){
        System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Depositar dinero
                    2) Retirar dinero
                    3) Realizar una transferencia
                    4) Mostrar datos de la cuenta
                    5) Eliminar cuenta
                    6) Cerrar sesión
                    7) Salir de la sucursal""");}
    private void menuAdmin(){
        System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Mostrar datos de esta sucursal
                    2) Mostrar datos de otra sucursal
                    3) Mostrar datos del banco
                    4) Crear sucursal
                     ""\");""");}
}






