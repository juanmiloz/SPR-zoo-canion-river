package co.edu.icesi.zoocanyonriver.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TigerCharacteristics {

    //WEIGHT
    MALE_MAX_WEIGHT(310),
    MALE_MIN_WEIGHT(1),
    FEMALE_MAX_WEIGHT(310),
    FEMALE_MIN_WEIGHT(1),

    //AGE
    MAX_AGE(26),
    MIN_AGE(0),

    //HEIGHT
    MAX_HEIGHT(122),
    MIN_HEIGHT(10);

    private int value;
}
