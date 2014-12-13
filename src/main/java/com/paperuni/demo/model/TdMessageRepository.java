package com.paperuni.demo.model;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TdMessage.class)
public interface TdMessageRepository {
}
