package com.paperuni.demo.web.selectoption;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.paperuni.demo.model.TdRole;
import com.paperuni.demo.model.TdRoleRepository;

@Service
public class Groupname {
	
	private static List<Groupname> groups;
	private String name;
	private String label;
	
	public Groupname(){

	}
	
	@Autowired
	public void setRoleRepository(TdRoleRepository roleRepository){
		List<Groupname> viewlist = new ArrayList<Groupname>();
		List<TdRole> roleList = roleRepository.findAll(activeRoles());
		if (roleList != null){
			for (TdRole r: roleList){
				viewlist.add(new Groupname(r.getName(), r.getDescription()));
			}
		}
		groups = viewlist;		
	}
	
	public Groupname(String name, String label){
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static List<Groupname> getAllGroups(){
		return groups;
	}
	
	private Specification<TdRole> activeRoles(){
		return new Specification<TdRole>(){
			
			@Override
			public Predicate toPredicate(Root<TdRole> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Boolean>get("isActivated"), true);
			}
		};
	}
}
