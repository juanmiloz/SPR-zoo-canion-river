package co.edu.icesi.zoocanyonriver.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TigerDemoError {

    private String code;
    private String message;
}
