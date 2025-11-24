package my.myPetClinic.repositories;

import my.myPetClinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastName);

    List<Owner> findByLastNameLike(String lastName);
}
