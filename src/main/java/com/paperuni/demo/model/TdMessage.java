package com.paperuni.demo.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(identifierType = TdMessagePK.class, versionField = "", table = "td_message")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "taskId" })
public class TdMessage {

    public TdMessage() {
        this.setHasRead(false);
        this.setHasReview(false);
    }
}
