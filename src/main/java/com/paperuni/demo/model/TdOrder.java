package com.paperuni.demo.model;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "td_order")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "tdTasks", "taskId", "subjectId" })
public class TdOrder {
	
   @Transient
   @Size(max = 100)
   private String url ;	
   
   public String getUrl(){
	   return url;
   }
   
   public void setUrl(String url){
	   this.url = url;
   }

    public TdOrder() {
        this.setOrderStatus("PENDING");
        Calendar c = new GregorianCalendar();
        this.setStartDate(c);
        c.add(GregorianCalendar.DAY_OF_MONTH, 1);
        this.setDeadLine(c);
        this.setWordCount(0);
    }
}
