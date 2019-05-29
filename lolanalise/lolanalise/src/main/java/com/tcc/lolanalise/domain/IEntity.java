package com.tcc.lolanalise.domain;

import java.io.Serializable;

public interface IEntity<ID> extends Serializable {

	ID getId();

	void setId(ID id);
}