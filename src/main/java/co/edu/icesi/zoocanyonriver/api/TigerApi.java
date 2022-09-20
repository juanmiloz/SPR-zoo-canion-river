package co.edu.icesi.zoocanyonriver.api;

import co.edu.icesi.zoocanyonriver.dto.TigerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tigers")
public interface TigerApi {

    @GetMapping("/{tigerName}")
    public List<TigerDTO> getTiger(@PathVariable String tigerName);

    @PostMapping()
    public TigerDTO createTiger(@RequestBody TigerDTO tigerDTO);

    @GetMapping()
    public List<TigerDTO> getTigers();
}
