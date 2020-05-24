package fr.society.template.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level=AccessLevel.PRIVATE)
public abstract class AbstractCreationModification<T extends Serializable> extends AbstractIdModel<T> {

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    Date creationDate;

    @Column(name = "MODIFICATION_DATE")
    @UpdateTimestamp
    Date modificationDate;

    @Column(name = "USER_PK_CREATION")
    Long userCreationId;

    @Column(name = "USER_PK_MODIFICATION")
    Long userModificationId;
}
