package com.paperuni.demo.model;
import java.math.BigDecimal;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "td_task")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "tdMessages", "tdOrders", "customerId", "orderId", "subjectId", "writerId" })
public class TdTask {

    public TdTask() {
        this.setStatus("PENDING");
    }
}
