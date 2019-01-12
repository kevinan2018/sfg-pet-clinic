package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.map.AbstractMapService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    protected Long getNextId() {
        //return Collections.max(map.keySet()) + 1;
        Long maxId = map.keySet().stream()
                .max((k1, k2) -> {
                    long l = k1 - k2;
                    return l < 0 ? -1 : (l > 0 ? 1 : 0);
                }).orElse(0L);

        return maxId + 1;
    }
}
