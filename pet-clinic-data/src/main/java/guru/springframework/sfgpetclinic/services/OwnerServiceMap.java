package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.map.AbstractMapService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Owner findByLastName(String lastName) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getLastName().equalsIgnoreCase(lastName))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }
}
