package com.tcc.lolanalise.domain;

import static org.hibernate.Hibernate.unproxy;

public abstract class BaseEntity<ID> implements IEntity<ID> {
	private static final long serialVersionUID = 1L;

	@Override
	public abstract ID getId();

	@Override
	public abstract void setId(ID id);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != unproxy(obj).getClass())
			return false;
		BaseEntity<?> other = (BaseEntity<?>) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}