package fr.society.template.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "T_POST")
@Entity
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE) // passe tous les champs en private
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="POST_PK")),
        @AttributeOverride(name="version", column=@Column(name="POST_VERSION")),
        @AttributeOverride(name="creationDate", column=@Column(name="POST_CREATION_DATE")),
        @AttributeOverride(name="modificationDate", column=@Column(name="POST_MODIFICATION_DATE"))
})
public class Post extends AbstractCreationModification<Long> {

    @Column(name = "POST_CONTENT")
    String content;

}
