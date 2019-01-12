package guru.springframework.sfgpetclinic.model;

public class PetType extends BaseEntity<Long> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long nextId(Long id) {
        return id == null ? 1 : id + 1;
    }
}
