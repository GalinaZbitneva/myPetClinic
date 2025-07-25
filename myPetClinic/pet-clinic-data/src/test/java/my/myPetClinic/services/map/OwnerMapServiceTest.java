package my.myPetClinic.services.map;

import my.myPetClinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    final long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        //с помощью нотации lombok установили id для Owner и сохранили в сервисе ownerMapService. т.е поместили в сервис один объект
        ownerMapService.save(Owner.builder().id(ownerId).lastName("Smith").build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void delete() {
        //в методе setUp я добавила один объект с ownerId, в этом тесте будем его удалять
        //поэтому ожидаемое згачение 0
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findById() {
       Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = new Owner().builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner owner = ownerMapService.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());

    }

    @Test
    void deleteById() {
        //в методе setUp я добавила один объект с ownerId, в этом тесте будем его удалять
        //поэтому ожидаемое згачение 0
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(lastName,smith.getLastName());
    }
    @Test
    void findByLastNameNotFound() {
        //в этом тесте ожидаем что объект не будет найден и получим null
        Owner smith = ownerMapService.findByLastName("xxxxx");
        assertNull(smith);
    }
}