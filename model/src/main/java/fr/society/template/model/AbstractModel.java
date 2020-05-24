package fr.society.template.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;


@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractModel<T extends Serializable> implements Serializable, Persistable<T> {

    @Transient
    public boolean isNew() {
        return null == getIdEntity();
    }

    @Transient
    protected abstract T getIdEntity();
}
