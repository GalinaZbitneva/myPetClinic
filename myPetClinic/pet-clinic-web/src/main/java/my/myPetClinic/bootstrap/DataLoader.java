package my.myPetClinic.bootstrap;

import my.myPetClinic.model.Owner;
import my.myPetClinic.model.Pet;
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

import java.time.LocalDate;

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
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1= new Owner();

        owner1.setFirstName("Galina");
        owner1.setLastName("Zbitneva");
        owner1.setAddress("Lubimovo 18/1");
        owner1.setCity("Krasnodar");
        owner1.setTelephone("1212121");

        Pet somePet = new Pet();
        somePet.setPetType(savedDogPetType);
        somePet.setOwner(owner1);
        somePet.setBirthDate(LocalDate.now());
        somePet.setName("Rosco");
        owner1.getPets().add(somePet);

        ownerService.save(owner1);

        Owner owner2=new Owner();

        owner2.setFirstName("Evgenii");
        owner2.setLastName("Zbitnev");
        owner2.setAddress("Lubimovo 18/1 181");
        owner2.setCity("Krasnodar 1");
        owner2.setTelephone("34353536");

        Pet anotherPet = new Pet();
        anotherPet.setPetType(savedCatPetType);
        anotherPet.setName("Murka");
        anotherPet.setBirthDate(LocalDate.now());
        anotherPet.setOwner(owner2);
        owner2.getPets().add(anotherPet);

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
