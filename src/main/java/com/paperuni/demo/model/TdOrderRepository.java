package com.paperuni.demo.model;
import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TdOrder.class)
public interface TdOrderRepository {
	
	List<TdOrder> findBycustomerId(Integer customerId);
}
