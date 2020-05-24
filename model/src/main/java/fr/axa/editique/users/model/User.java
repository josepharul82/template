package fr.axa.editique.users.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "T_USER")
@Entity
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE) // passe tous les champs en private
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="USER_PK")),
        @AttributeOverride(name="version", column=@Column(name="USER_VERSION")),
        @AttributeOverride(name="creationDate", column=@Column(name="USER_CREATION_DATE")),
        @AttributeOverride(name="modificationDate", column=@Column(name="USER_MODIFICATION_DATE"))
})
public class User extends AbstractCreationModification<Long> {

    @Column(name = "USER_FIRST_NAME")
    String firstName;

    @Column(name = "USER_LAST_NAME")
    String lastName;

    @Column(name = "USER_MAIL")
    String mail;

    @ManyToMany
    @JoinTable(
            name = "T_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_PK"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_PK"))
    Set<Role> roleList;

    @OneToMany
    @JoinColumn(name = "USER_PK")
    Set<Post> postList;

}
