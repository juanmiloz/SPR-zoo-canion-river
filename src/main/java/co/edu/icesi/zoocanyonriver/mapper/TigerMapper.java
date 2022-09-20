package co.edu.icesi.zoocanyonriver.mapper;

import co.edu.icesi.zoocanyonriver.dto.TigerDTO;
import co.edu.icesi.zoocanyonriver.model.Tiger;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TigerMapper {
    Tiger fromDTO(TigerDTO tigerDTO);
    TigerDTO fromTiger(Tiger tiger);
}
