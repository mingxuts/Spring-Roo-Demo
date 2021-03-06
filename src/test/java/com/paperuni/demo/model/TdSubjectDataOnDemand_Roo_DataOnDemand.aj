// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdSubject;
import com.paperuni.demo.model.TdSubjectDataOnDemand;
import com.paperuni.demo.model.TdSubjectRepository;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TdSubjectDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TdSubjectDataOnDemand: @Component;
    
    private Random TdSubjectDataOnDemand.rnd = new SecureRandom();
    
    private List<TdSubject> TdSubjectDataOnDemand.data;
    
    @Autowired
    TdSubjectRepository TdSubjectDataOnDemand.tdSubjectRepository;
    
    public TdSubject TdSubjectDataOnDemand.getNewTransientTdSubject(int index) {
        TdSubject obj = new TdSubject();
        setDescription(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void TdSubjectDataOnDemand.setDescription(TdSubject obj, int index) {
        String description = "description_" + index;
        if (description.length() > 70) {
            description = description.substring(0, 70);
        }
        obj.setDescription(description);
    }
    
    public void TdSubjectDataOnDemand.setName(TdSubject obj, int index) {
        String name = "name_" + index;
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
        obj.setName(name);
    }
    
    public TdSubject TdSubjectDataOnDemand.getSpecificTdSubject(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        TdSubject obj = data.get(index);
        Integer id = obj.getId();
        return tdSubjectRepository.findOne(id);
    }
    
    public TdSubject TdSubjectDataOnDemand.getRandomTdSubject() {
        init();
        TdSubject obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getId();
        return tdSubjectRepository.findOne(id);
    }
    
    public boolean TdSubjectDataOnDemand.modifyTdSubject(TdSubject obj) {
        return false;
    }
    
    public void TdSubjectDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = tdSubjectRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'TdSubject' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<TdSubject>();
        for (int i = 0; i < 10; i++) {
            TdSubject obj = getNewTransientTdSubject(i);
            try {
                tdSubjectRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            tdSubjectRepository.flush();
            data.add(obj);
        }
    }
    
}
