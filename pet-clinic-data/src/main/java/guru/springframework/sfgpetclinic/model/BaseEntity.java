package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public abstract class BaseEntity <T extends Comparable<? super T>> implements Serializable, Comparable<BaseEntity<T>> {

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
