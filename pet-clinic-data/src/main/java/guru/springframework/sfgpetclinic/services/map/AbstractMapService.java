package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity<ID>, ID extends Comparable<? super ID>> {

    protected Map<ID, T> map = new HashMap<>();

    public Set<T> findAll() {
        return  new HashSet<>(map.values());
    }

    public T findById(ID id) {
        return map.get(id);
    }

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

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

    protected ID getMaxId() {
         return map.keySet().stream()
                .max(Comparator.naturalOrder()) //(n1, n2) -> n1.compareTo(n2)
                .orElse(null);
    }

}
