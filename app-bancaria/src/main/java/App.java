import entity.Banco;
import entity.UsuarioCliente;
import interfaz.Menu;
import repository.SucursalRepository;
import repository.TransaccionRepository;
import repository.UsuarioClienteRepository;
import service.SucursalService;
import service.TransaccionService;
import service.UsuarioClienteService;

public class App {
    public static void main(String[] args) {

        Menu menu = init();
        menu.mostrarMenu();
    }

    private static Menu init(){
        Banco bancoPatagonia = Banco.getInstancia();
        UsuarioClienteRepository userRepo = new UsuarioClienteRepository();
        TransaccionRepository transRepo = new TransaccionRepository();
        SucursalRepository sucRepo = new SucursalRepository();
        SucursalService sucService = new SucursalService(sucRepo);
        TransaccionService transService = new TransaccionService(userRepo, transRepo);
        UsuarioClienteService userService = new UsuarioClienteService(userRepo);

        return new Menu(userService, transService, sucService);
    }
}
