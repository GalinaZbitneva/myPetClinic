package my.myPetClinic.model;

import org.springframework.stereotype.Service;

import java.util.Set;

public class Vet extends Person{
    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
