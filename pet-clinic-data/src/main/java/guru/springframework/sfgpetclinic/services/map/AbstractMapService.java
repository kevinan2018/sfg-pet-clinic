package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity<ID>, ID extends Comparable<? super ID>> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return  new HashSet<>(map.values());
    }

    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    @Override
    public T save(T object) {

        if (object != null) {
            if (object.getId() == null) {
                object.setId(object.nextId(getMaxId()));
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    @Override
    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }

    private ID getMaxId() {
         return map.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

}
