package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class BaseEntity <T>implements Serializable {

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
