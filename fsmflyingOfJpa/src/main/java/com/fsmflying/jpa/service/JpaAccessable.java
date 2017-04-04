package com.fsmflying.jpa.service;

import javax.persistence.EntityManagerFactory;

public interface JpaAccessable {
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
}
