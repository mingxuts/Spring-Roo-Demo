package com.paperuni.demo.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "td_subject")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "tdOrders", "tdTasks" })
public class TdSubject {
}
