package com.paperuni.demo.model;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TdMessage.class)
public interface TdMessageRepository {
	
	List<TdMessage> findByTaskId_CustomerId(TdUserinfo userinfo);
	List<TdMessage> findByTaskId_CustomerId(TdUserinfo userinfo, Pageable pageable);
	List<TdMessage> findByTaskId_CustomerIdAndSourceOrderByCreateDateDesc(TdUserinfo userinfo, String source);
	List<TdMessage> findTop10ByTaskId_CustomerIdAndSourceOrderByCreateDateDesc(TdUserinfo userinfo, String source, Pageable pageable);
}
