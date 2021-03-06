// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdSubject;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect TdSubject_Roo_DbManaged {
    
    @OneToMany(mappedBy = "subjectId")
    private Set<TdOrder> TdSubject.tdOrders;
    
    @Column(name = "Description", length = 70)
    private String TdSubject.description;
    
    @Column(name = "Name", length = 20)
    @NotNull
    private String TdSubject.name;
    
    public Set<TdOrder> TdSubject.getTdOrders() {
        return tdOrders;
    }
    
    public void TdSubject.setTdOrders(Set<TdOrder> tdOrders) {
        this.tdOrders = tdOrders;
    }
    
    public String TdSubject.getDescription() {
        return description;
    }
    
    public void TdSubject.setDescription(String description) {
        this.description = description;
    }
    
    public String TdSubject.getName() {
        return name;
    }
    
    public void TdSubject.setName(String name) {
        this.name = name;
    }
    
}
