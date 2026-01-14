package my.myPetClinic.services.map;

import my.myPetClinic.model.Pet;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {
    private PetMapService petMapService;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        Pet pet = new Pet();
        pet.setId(petId);
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void findById() {
        Pet findPet = petMapService.findById(petId);
        assertEquals(findPet.getId(),petId);
    }

    @Test
    void findByNotExistingId(){
        Pet pet = petMapService.findById(5L);
        assertNull(pet);
    }

    @Test
    void saveDuplicatedId(){
        Pet pet2 = new Pet();
        pet2.setId(1L);
        petMapService.save(pet2);
        //проверяем что у нового объекта такой же id как у существующего
        assertEquals(petId, pet2.getId());
        //проверяем что новый объект не сохранился в сервисе
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void save() {
        Pet newPet = new Pet();
        newPet.setId(2L);
        petMapService.save(newPet);
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveNoId(){
        Pet pet2 = new Pet();
        petMapService.save(pet2);
        assertEquals(2, petMapService.findAll().size());

    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByWrongId(){
        petMapService.deleteById(5L);
        //проверяем что ничего не изменилось
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteNullObject(){
        petMapService.delete(null);
        assertEquals(1, petMapService.findAll().size());
    }



}