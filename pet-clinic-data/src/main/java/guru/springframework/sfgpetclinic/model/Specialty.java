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
@Table(name = "specialties")
public class Specialty extends BaseEntity<Long> {

    @Column(name = "description")
    private String description;

    @Override
    public Long nextId(Long id) {
        return id == null ? 1 : id + 1;
    }
}
