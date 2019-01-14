package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER, cascade = {MERGE, REFRESH})
    @JoinTable(name = "vet_specialties", joinColumns = { @JoinColumn(name = "vet_id")}, inverseJoinColumns = { @JoinColumn(name = "specialty_id") })
    //@Builder.Default
    final private Set<Specialty> specialties = new HashSet<>();
}
