package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> listarStock(){
        return stockRepository.findAll();
    }

    public Stock guardarStock(Stock stock){
        return stockRepository.save(stock);
    }

    public Stock buscarPorId(Integer id){
        return stockRepository.findById(id).orElse(null);
    }

    public void eliminarStock(Integer id){
        stockRepository.deleteById(id);

    }

    public Stock findByCarta(Carta carta){return stockRepository.findByCarta(carta);
    }
}
