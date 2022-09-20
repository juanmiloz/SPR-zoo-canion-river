package co.edu.icesi.zoocanyonriver.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodesError {
    CODE_O1("CODE_01", "The name has character that are not allowed"),
    CODE_02("CODE_02", "The name is longer than allowed"),
    CODE_03("CODE_03", "The tiger you are looking for is not registered in the system"),
    CODE_04("CODE_04", "The id of the tiger you are looking for is not registered in the system"),
    CODE_05("CODE_05", "The weight of the male tiger you wish to enter is incorrect"),
    CODE_06("CODE_06", "The weight of the female tiger you wish to enter is incorrect"),
    CODE_07("CODE_07", "The genre I am trying to enter is not allowed"),
    CODE_08("CODE_08", "The age entered is not allowed"),
    CODE_09("CODE_09", "The height entered is outside the established ranges"),
    CODE_10("CODE_10", "The gender of the parents is the same"),
    CODE_11("CODE_11", "The date entered is greater than current date"),
    CODE_12("CODE_12", "The name you entered is already registered, please change the name");


    private String code;
    private String message;
}
