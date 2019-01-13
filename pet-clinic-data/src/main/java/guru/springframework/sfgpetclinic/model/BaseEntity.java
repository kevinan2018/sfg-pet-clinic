package guru.springframework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity <T extends Comparable<? super T>> implements Serializable, Comparable<BaseEntity<T>> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public int compareTo(BaseEntity<T> other) {
        return id.compareTo(other.id);
    }

    abstract public T nextId(T t);
}
