package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity<ID>, ID> {

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
                object.setId(getNextId());
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

    abstract protected ID getNextId();

    // hackish code
    private ID getMaxId() {
         return map.keySet().stream()
                .filter(key -> key instanceof Number)
                .max((n1, n2) -> {
                    double dv = ((Number) n1).longValue() - ((Number) n2).longValue();
                    if (dv < 0) return -1;
                    else if (dv > 0) return 1;
                    else return 0;
                })
                .orElse(null);
    }


}
