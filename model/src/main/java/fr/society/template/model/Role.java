package fr.society.template.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Table(name = "T_ROLE")
@Entity
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE) // passe tous les champs en private
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="ROLE_PK")),
        @AttributeOverride(name="version", column=@Column(name="ROLE_VERSION")),
        @AttributeOverride(name="creationDate", column=@Column(name="ROLE_CREATION_DATE")),
        @AttributeOverride(name="modificationDate", column=@Column(name="ROLE_MODIFICATION_DATE"))
})
public class Role extends AbstractCreationModification<Long>{

    @Column(name = "ROLE_NAME")
    String name;

}
