package fr.society.template.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(of = {"id"})
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractIdModel<T extends Serializable> extends AbstractModel<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private T id;

    @Version
    @Getter
    @Setter
    Integer version;

    @Transient
    public T getIdEntity() {
        return getId();
    }

}
