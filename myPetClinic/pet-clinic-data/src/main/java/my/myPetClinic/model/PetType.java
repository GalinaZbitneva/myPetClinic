package my.myPetClinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity{

    public  PetType(Long id, String name){
        this.setId(id);
        this.name = name;
    }

    public PetType() {
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
