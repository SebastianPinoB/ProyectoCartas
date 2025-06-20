package ProyectoCartas;

import ProyectoCartas.modelo.*;
import ProyectoCartas.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private CartaRepository cartaRepo;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private CompraRepository compraRepo;

    @Autowired
    private BoletaRepository boletaRepo;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Lista de digitos verificadores de tipo String
        String[] dvRun = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "K"};

        // Generar clientes
        for (int i = 1; i <= 20; i++) {
            // run completo es basicamente una concatenacion en una variable, no hay tanta ciencia
            int numRun = faker.number().numberBetween(10000000, 30000000);
            String runCompleto = numRun + "-" + dvRun[random.nextInt(dvRun.length)]; // basicamente da una posicion de la lista de manera aleatoria

            Cliente cliente = new Cliente();
            cliente.setRun(runCompleto);
            cliente.setNombre(faker.name().fullName());
            clienteRepo.save(cliente);
        }

        List<Cliente> clientes = clienteRepo.findAll();


        // Generar cartas
        for (int i = 1; i <= 20; i++) {
            Carta carta = new Carta();
            carta.setNombre(faker.pokemon().name());
            carta.setCodigoExp("COD-0" + i);
            carta.setPrecio(faker.number().numberBetween(10000, 999999));
            cartaRepo.save(carta);
        }

        List<Carta> cartas = cartaRepo.findAll();

        // Generar Stock
        // Las relaciones @OneToOne debe recorre la tabla fk (Carta y Compra) osea "Carta" para evitar un error de duplicidad en los datos
        // Si tienen dudas revisen las tablas Stock y Boleta, estas son relaciones uno a uno, pero ustedes pueden probar con el for i.
        for (Carta carta : cartas) {
            Stock stock = new Stock();
            stock.setCarta(carta);
            stock.setCantidad(faker.number().numberBetween(10, 999));
            stockRepo.save(stock);
        }

        // Generar Compra
        for (int i = 1; i <= 5; i++) {
            Compra compra = new Compra();
            compra.setCarta(cartas.get(random.nextInt(cartas.size())));
            compra.setCliente(clientes.get(random.nextInt(clientes.size())));
            compraRepo.save(compra);
        }

        List<Compra> compras = compraRepo.findAll();

        // Generar Boleta
        for (Compra compra : compras) {
            Boleta boleta = new Boleta();
            boleta.setCompra(compra);
            boletaRepo.save(boleta);
        }

    }
}
