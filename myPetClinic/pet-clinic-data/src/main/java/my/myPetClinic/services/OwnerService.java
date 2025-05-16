package my.myPetClinic.services;

import my.myPetClinic.model.Owner;



public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

}
