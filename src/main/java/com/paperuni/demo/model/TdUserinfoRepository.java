package com.paperuni.demo.model;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TdUserinfo.class)
public interface TdUserinfoRepository {
	
	public TdUserinfo findByEmail(String email); 
}
