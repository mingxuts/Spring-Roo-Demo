package com.paperuni.demo.model;
import java.math.BigDecimal;

import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "td_task")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "tdMessages", "tdOrders", "customerId", "orderId", "subjectId", "writerId" })
public class TdTask {
	
   @Transient
   @Size(max = 100)
   private String url ;	
   
   public String getUrl(){
	   return url;
   }
   
   public void setUrl(String url){
	   this.url = url;
   }	

    public TdTask() {
        this.setStatus("PENDING");
    }
}
