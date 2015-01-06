package com.paperuni.demo.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

	public static Specification<TdTask> byCustomer(final TdUserinfo userinfo){
		
		return new Specification<TdTask>(){
			
			@Override
			public Predicate toPredicate(Root<TdTask> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<TdUserinfo>get("customerId"), userinfo);
			}
		};
	}
		

}
