package my.myPetClinic.bootstrap;

import my.myPetClinic.model.Owner;
import my.myPetClinic.model.PetType;
import my.myPetClinic.model.Vet;
import my.myPetClinic.services.OwnerService;
import my.myPetClinic.services.PetTypeService;
import my.myPetClinic.services.VetService;
import my.myPetClinic.services.map.OwnerServiceMap;
import my.myPetClinic.services.map.VetServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1= new Owner();

        owner1.setFirstName("Galina");
        owner1.setLastName("Zbitneva");

        ownerService.save(owner1);

        Owner owner2=new Owner();

        owner2.setFirstName("Evgenii");
        owner2.setLastName("Zbitnev");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();

        vet1.setFirstName("Ivan");
        vet1.setLastName("Ivanov");

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Kate");
        vet2.setLastName("Petrova");

        vetService.save(vet2);

        System.out.println("Loaded vets....");
        System.out.println(vetService.findAll().size());






    }
}
