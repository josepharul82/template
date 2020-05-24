package fr.axa.editique.users.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractEmbeddedIdModel<T extends Serializable> extends AbstractModel<T> {

	@EmbeddedId
	@Getter
	@Setter
	private T id;

	@Override
	@Transient
	public T getIdEntity(){
		return getId();
	}
}
