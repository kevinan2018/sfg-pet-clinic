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

    @Override
    protected Long getNextId() {
        // return Collections.max(map.keySet()) + 1;
        Long maxId = map.keySet().stream()
                .max((k1, k2) -> {
                    long l = k1 - k2;
                    return l < 0 ? -1 : (l > 0 ? 1 : 0);
                }).orElse(0L);

        return maxId + 1;
    }
}
