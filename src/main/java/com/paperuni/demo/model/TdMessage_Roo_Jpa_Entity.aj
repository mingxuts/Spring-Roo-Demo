// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdMessagePK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect TdMessage_Roo_Jpa_Entity {
    
    declare @type: TdMessage: @Entity;
    
    declare @type: TdMessage: @Table(name = "td_message");
    
    @EmbeddedId
    private TdMessagePK TdMessage.id;
    
    public TdMessagePK TdMessage.getId() {
        return this.id;
    }
    
    public void TdMessage.setId(TdMessagePK id) {
        this.id = id;
    }
    
}
