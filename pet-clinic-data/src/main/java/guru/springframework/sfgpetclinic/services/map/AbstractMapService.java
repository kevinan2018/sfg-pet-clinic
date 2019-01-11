package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity<ID>, ID> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return  new HashSet<>(map.values());
    }

    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    //    T save(ID id, T object){
    //        map.put(id, object);
    //        return object;
    //    }

    @Override
    public T save(T object) {
        map.put(object.getId(), object);
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

}
