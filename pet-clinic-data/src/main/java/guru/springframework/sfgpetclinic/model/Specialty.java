package guru.springframework.sfgpetclinic.model;

public class Specialty extends BaseEntity<Long> {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long nextId(Long id) {
        return id == null ? 1 : id + 1;
    }
}
