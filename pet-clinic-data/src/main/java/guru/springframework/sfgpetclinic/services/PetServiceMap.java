package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.map.AbstractMapService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
}
