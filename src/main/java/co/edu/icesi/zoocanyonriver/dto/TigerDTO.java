package co.edu.icesi.zoocanyonriver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TigerDTO {

    private UUID id;

    private String uuidParent1;

    private String uuidParent2;

    private String name;

    private String gender;

    private double weight;

    private String age;

    private String height;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arriveDate;
}
