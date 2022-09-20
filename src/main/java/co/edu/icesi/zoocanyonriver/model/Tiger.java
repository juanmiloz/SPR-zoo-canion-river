package co.edu.icesi.zoocanyonriver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tiger {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String uuidParent1;

    private String uuidParent2;

    private String name;

    private String gender;

    private double weight;

    private String age;

    private String height;

    private LocalDateTime arriveDate;

    @PrePersist
    public void generateId() { this.id = UUID.randomUUID();}
}
