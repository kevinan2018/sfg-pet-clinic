package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class PetType extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Override
    public Long nextId(Long id) {
        return id == null ? 1 : id + 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
