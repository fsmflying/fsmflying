package com.fsmflying.hibernate.jpa;

import javax.persistence.EntityManagerFactory;

public interface HibernateJpaAccessable {
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
}
