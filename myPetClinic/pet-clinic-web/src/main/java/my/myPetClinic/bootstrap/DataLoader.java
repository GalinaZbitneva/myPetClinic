package my.myPetClinic.bootstrap;

import my.myPetClinic.model.Owner;
import my.myPetClinic.model.Vet;
import my.myPetClinic.services.OwnerService;
import my.myPetClinic.services.VetService;
import my.myPetClinic.services.map.OwnerServiceMap;
import my.myPetClinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1= new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Galina");
        owner1.setLastName("Zbitneva");

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Evgenii");
        owner2.setLastName("Zbitnev");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Ivan");
        vet1.setLastName("Ivanov");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Kate");
        vet2.setLastName("Petrova");

        vetService.save(vet2);

        System.out.println("Loaded vets....");






    }
}
