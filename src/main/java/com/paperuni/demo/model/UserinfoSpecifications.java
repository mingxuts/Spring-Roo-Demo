package com.paperuni.demo.model;

import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class UserinfoSpecifications {
	
	public static Specification<TdUserinfo> emailEqual(final String email){
		
		return new Specification<TdUserinfo>(){
			
			@Override
			public Predicate toPredicate(Root<TdUserinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get("email"), email);
			}
		};
	}

}
