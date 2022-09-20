package co.edu.icesi.zoocanyonriver.service.impl;

import co.edu.icesi.zoocanyonriver.constants.CodesError;
import co.edu.icesi.zoocanyonriver.constants.Genders;
import co.edu.icesi.zoocanyonriver.constants.TigerCharacteristics;
import co.edu.icesi.zoocanyonriver.error.exception.TigerDemoError;
import co.edu.icesi.zoocanyonriver.error.exception.TigerDemoException;
import co.edu.icesi.zoocanyonriver.model.Tiger;
import co.edu.icesi.zoocanyonriver.repository.TigerRepository;
import co.edu.icesi.zoocanyonriver.service.TigerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Macho:
 * -Peso:90-310kg
 * -edad:26 años
 * -estatura:80-110cm
 * Hembra:
 * -Peso:65-170kg
 * -edad:26 años
 * -estaturas:80-110cm
 */
@AllArgsConstructor
@Service
public class TigerServiceImpl implements TigerService {

    private final TigerRepository tigerRepository;

    @Override
    public Tiger getTiger(String tigerName) {
        return searchByName(tigerName,StreamSupport.stream(tigerRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public Tiger getTiger(UUID tigerId) {
        Optional<Tiger> tiger= tigerRepository.findById(tigerId);
        if(tiger.isPresent()){
            return tiger.get();
        }
        throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_04.getCode(), CodesError.CODE_04.getMessage()));
    }
    private Tiger searchByName(String tigerName, List<Tiger> tigers){
        for(Tiger tiger: tigers){
            if(tiger.getName().equalsIgnoreCase(tigerName)){return tiger;}
        }
        throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_03.getCode(), CodesError.CODE_03.getMessage()));
    }

    @Override
    public Tiger createTiger(Tiger tiger) {
        verifyExistenceParents(tiger);
        verifyNameRepeat(tiger);
        verifyWeightGender(tiger);
        verifyAge(tiger.getAge());
        verifyHeight(tiger.getHeight());
        verifyDate(tiger.getArriveDate());

        return tigerRepository.save(tiger);
    }

    private void verifyNameRepeat(Tiger tiger) {
        List<Tiger> tigers = StreamSupport.stream(tigerRepository.findAll().spliterator(), false).collect(Collectors.toList());
        for(Tiger currentTiger: tigers){
            if(currentTiger.getName().equalsIgnoreCase(tiger.getName())){
                throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_12.getCode(), CodesError.CODE_12.getMessage()));
            }
        }
    }

    private void verifyExistenceParents(Tiger tiger){
        if(tiger.getUuidParent1() != null){
            getTiger(UUID.fromString(tiger.getUuidParent1()));
        }
        if(tiger.getUuidParent2() != null){
            getTiger(UUID.fromString(tiger.getUuidParent2()));
        }
        verifyDifferentGenres(tiger);
    }

    private void verifyDifferentGenres(Tiger tiger){
        String uuidParent1 = tiger.getUuidParent1();
        String uuidParent2 = tiger.getUuidParent2();

        if(uuidParent1 != null && uuidParent2 != null){
            if(getGenreParent(uuidParent1).equals(getGenreParent(uuidParent2))){
                throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_10.getCode(), CodesError.CODE_10.getMessage()));
            }
        }
    }

    private String getGenreParent(String uuid){
        return getTiger(UUID.fromString(uuid)).getGender();
    }

    private void verifyGenderParents(){
        /*String uuidParent1 = tiger.getUuidParent1();
        String uuidParent2 = tiger.getUuidParent2();
        if(uuidParent1 != null && uuidParent2 != null){
            verifyGenderParents();
            verifyGenderParents();
        }*/
    }


    private void verifyHeight(String height) {
        int a = Integer.valueOf(height);

        if(Integer.valueOf(height)> TigerCharacteristics.MAX_HEIGHT.getValue() ||Integer.valueOf(height)< TigerCharacteristics.MIN_HEIGHT.getValue()){
            throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_09.getCode(), CodesError.CODE_09.getMessage()));
        }
    }

    private void verifyAge(String age) {
        if(Integer.parseInt(age)>TigerCharacteristics.MAX_AGE.getValue()||Integer.parseInt(age)<TigerCharacteristics.MIN_AGE.getValue()){
            throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_08.getCode(), CodesError.CODE_08.getMessage()));
        }
    }

    private void verifyWeightGender(Tiger tiger){
        if(tiger.getGender().equalsIgnoreCase(Genders.MALE.getValue())){
            verifyWeightMale(tiger.getWeight());
        }else if(tiger.getGender().equalsIgnoreCase(Genders.FEMALE.getValue())){
            verifyWeightFemale(tiger.getWeight());
        }else{
            throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_07.getCode(), CodesError.CODE_07.getMessage()));
        }
    }

    private void verifyWeightMale(double weight){
        if(weight>TigerCharacteristics.MALE_MAX_WEIGHT.getValue()||weight<TigerCharacteristics.MALE_MIN_WEIGHT.getValue()){
            throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_05.getCode(), CodesError.CODE_05.getMessage()));
        }
    }

    private void verifyDate(LocalDateTime arriveDate) {
        if(arriveDate.isAfter(LocalDateTime.now())){throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_11.getCode(), CodesError.CODE_11.getMessage()));}
    }

    private void verifyWeightFemale(double weight){
        if(weight>TigerCharacteristics.FEMALE_MAX_WEIGHT.getValue()||weight<TigerCharacteristics.FEMALE_MIN_WEIGHT.getValue()){
            throw new TigerDemoException(HttpStatus.BAD_REQUEST, new TigerDemoError(CodesError.CODE_06.getCode(), CodesError.CODE_06.getMessage()));
        }
    }

    @Override
    public List<Tiger> getTigers() {
        return StreamSupport.stream(tigerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
