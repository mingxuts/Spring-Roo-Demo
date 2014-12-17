package com.paperuni.demo.model;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;

public class OrderSpecifications {
	
	public static Specification<TdOrder> byCustomer(final int customerId){
		
		return new Specification<TdOrder>(){
			
			@Override
			public Predicate toPredicate(Root<TdOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Integer>get("customerId"), new Integer(customerId));
			}
		};
	}
	
	public static Specification<TdOrder> isPendingAndUnassign(){
		
		return new Specification<TdOrder>(){
			
			@Override
			public Predicate toPredicate(Root<TdOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				Predicate pending = cb.equal(root.<String>get("orderStatus"), "PENDING");
				Predicate unassign = cb.and(pending, cb.isNull(root.get("taskId")));
				return unassign;
			}
		};
	}

}
