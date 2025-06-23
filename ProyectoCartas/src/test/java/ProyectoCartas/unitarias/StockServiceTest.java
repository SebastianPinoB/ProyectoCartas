package ProyectoCartas.unitarias;

import ProyectoCartas.modelo.Stock;
import ProyectoCartas.modelo.Carta;

import ProyectoCartas.repository.StockRepository;
import ProyectoCartas.service.StockService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")

public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @MockitoBean
    private StockRepository stockRepository;

    @Test
    public void testFindAll(){
        Stock stock = new Stock();
        when(stockRepository.findAll()).thenReturn(List.of(stock));

        List<Stock> stocks = stockService.listarStock();
        assertNotNull(stocks);
        assertEquals(1, stocks.size());
    }

    @Test
    public void testFindById(){
        Integer id = 1;
        Stock stock = new Stock();
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        Stock encontrado = stockService.buscarPorId(id);
        assertNotNull(encontrado);
        assertEquals(1, encontrado.getIdStock());
    }

    @Test
    public void testSave(){
        Stock stock = crearStock();
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock guardar = stockService.guardarStock(stock);

        assertNotNull(guardar);
        assertEquals("Mew Plateado", guardar.getCarta().getNombre());
    }

    @Test
    public void testDeleteById(){
        Integer id = 1;
        Stock stock = new Stock();
        doNothing().when(stockRepository).deleteById(id);

        stockService.eliminarStock(id);
        verify(stockRepository, times(1)).deleteById(id);
    }

    private Stock crearStock(){
        Carta carta = new Carta();
        carta.setNombre("Mew Plateado");
        carta.setCodigoExp("COD-0122");
        carta.setPrecio(1000);

        Stock stock = new Stock();
        stock.setCarta(carta);
        stock.setCantidad(100);

        return stock;
    }
}
