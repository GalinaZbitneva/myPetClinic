package my.myPetClinic.services.springdatajpa;

import my.myPetClinic.model.Owner;
import my.myPetClinic.repositories.OwnerRepository;
import my.myPetClinic.repositories.PetRepository;
import my.myPetClinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDjpaServiceTest {
    public static final String LAST_NAME = "Smith";
    //это должно заменить констуктор и метод setUp
    @InjectMocks
    OwnerSDjpaService service;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    Owner testOwner;


    @BeforeEach
    void setUp() {
        testOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(testOwner);
        //проверяем работу сервиса для него тесты
        Owner smith = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME,smith.getLastName());
        verify(ownerRepository,times(1)).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> testOwnerSet = new HashSet<>();
        testOwnerSet.add(Owner.builder().id(1L).build());
        testOwnerSet.add(Owner.builder().id(2L).build());
        //говорим, что когда сработает ownerRepository.findAll() нужно вернуть testOwnerSet
        when(ownerRepository.findAll()).thenReturn(testOwnerSet);

        //owners должен пристовиться сет testOwnerSet
        Set<Owner> owners = service.findAll();
        //проверяем что owners не пуст
        assertNotNull(owners);
        //ожидаем, что в owners размер равен 2 и проверяем это
        assertEquals(2,owners.size());

    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(testOwner));
        //ожидаем, что owner присвоится значения  testOwner
        Owner owner = service.findById(1L);
        //если все пристовилось верно, то  assertNotNull(owner) не должен быть Null
        assertNotNull(owner);

    }
    @Test
    void findByIdNotFound() {
        //тут говорим что нужно вернуть null при вызове ownerRepository.findById
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);
        //поскольку метод when должен вернуть null, то owner не будет найден
        assertNull(owner);

    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(testOwner);
        Owner savedOwner = ownerRepository.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository,times(1)).save(any());
    }

    @Test
    void delete() {
        ownerRepository.delete(testOwner);
        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerRepository.deleteById(testOwner.getId());
        verify(ownerRepository,times(1)).deleteById(anyLong());
    }
}