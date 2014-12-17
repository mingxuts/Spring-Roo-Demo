package com.paperuni.demo.model;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.domain.Specification;

public class MessageSpecifications {
	
	public static Specification<TdMessage> byCustomerAndSource(final TdUserinfo userinfo, final String source){
		
		return new Specification<TdMessage>(){
			
			@Override
			public Predicate toPredicate(Root<TdMessage> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				Predicate equalUser= cb.equal(root.get("taskId").<TdUserinfo>get("customerId"), userinfo);
				Predicate equalSource = cb.and(equalUser, cb.equal(root.<String>get("source"), source));
				return equalSource;
				
			}
		};
	}
	
	public static Specification<TdMessage> byTaskId(final int taskId){
		
		return new Specification<TdMessage>(){
			
			@Override
			public Predicate toPredicate(Root<TdMessage> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.get("taskId").<Integer>get("id"), new Integer(taskId));
			}
		};
	}

}
