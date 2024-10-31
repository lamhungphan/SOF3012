package com.fpoly.sof3012.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJpa {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("default");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
