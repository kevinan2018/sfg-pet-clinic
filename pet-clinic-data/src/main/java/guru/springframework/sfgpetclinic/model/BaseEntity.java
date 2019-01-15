package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity <T extends Comparable<? super T>> implements Serializable, Comparable<BaseEntity<T>> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private T id;

    @Override
    public int compareTo(BaseEntity<T> other) {
        return id.compareTo(other.id);
    }

    public boolean isNew() {
        return id == null;
    }

    abstract public T nextId(T t);
}
