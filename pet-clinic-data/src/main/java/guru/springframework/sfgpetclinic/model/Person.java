package guru.springframework.sfgpetclinic.model;

public class Person extends BaseEntity<Long> {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Long nextId(Long id) {
        return id == null ? 1 : id + 1;
    }
}
