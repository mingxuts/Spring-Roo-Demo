// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoDataOnDemand;
import com.paperuni.demo.model.TdUserinfoRepository;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect TdUserinfoDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TdUserinfoDataOnDemand: @Component;
    
    private Random TdUserinfoDataOnDemand.rnd = new SecureRandom();
    
    private List<TdUserinfo> TdUserinfoDataOnDemand.data;
    
    @Autowired
    TdUserinfoRepository TdUserinfoDataOnDemand.tdUserinfoRepository;
    
    public TdUserinfo TdUserinfoDataOnDemand.getNewTransientTdUserinfo(int index) {
        TdUserinfo obj = new TdUserinfo();
        setCity(obj, index);
        setContactId(obj, index);
        setContactType(obj, index);
        setCountry(obj, index);
        setCreateBy(obj, index);
        setCreateDate(obj, index);
        setEmail(obj, index);
        setEmailCode(obj, index);
        setGroupName(obj, index);
        setHasVerified(obj, index);
        setLoginPassword(obj, index);
        setPasswordNonExpired(obj, index);
        setPreferName(obj, index);
        setSpecial(obj, index);
        return obj;
    }
    
    public void TdUserinfoDataOnDemand.setCity(TdUserinfo obj, int index) {
        String city = "city_" + index;
        if (city.length() > 35) {
            city = city.substring(0, 35);
        }
        obj.setCity(city);
    }
    
    public void TdUserinfoDataOnDemand.setContactId(TdUserinfo obj, int index) {
        String contactId = "contactId_" + index;
        if (contactId.length() > 35) {
            contactId = contactId.substring(0, 35);
        }
        obj.setContactId(contactId);
    }
    
    public void TdUserinfoDataOnDemand.setContactType(TdUserinfo obj, int index) {
        String contactType = "contac_" + index;
        if (contactType.length() > 8) {
            contactType = contactType.substring(0, 8);
        }
        obj.setContactType(contactType);
    }
    
    public void TdUserinfoDataOnDemand.setCountry(TdUserinfo obj, int index) {
        String country = "country_" + index;
        if (country.length() > 50) {
            country = country.substring(0, 50);
        }
        obj.setCountry(country);
    }
    
    public void TdUserinfoDataOnDemand.setCreateBy(TdUserinfo obj, int index) {
        Integer createBy = new Integer(index);
        obj.setCreateBy(createBy);
    }
    
    public void TdUserinfoDataOnDemand.setCreateDate(TdUserinfo obj, int index) {
        Calendar createDate = Calendar.getInstance();
        obj.setCreateDate(createDate);
    }
    
    public void TdUserinfoDataOnDemand.setEmail(TdUserinfo obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 65) {
            email = email.substring(0, 65);
        }
        obj.setEmail(email);
    }
    
    public void TdUserinfoDataOnDemand.setEmailCode(TdUserinfo obj, int index) {
        String emailCode = "foo" + index + "@bar.com";
        if (emailCode.length() > 50) {
            emailCode = emailCode.substring(0, 50);
        }
        obj.setEmailCode(emailCode);
    }
    
    public void TdUserinfoDataOnDemand.setGroupName(TdUserinfo obj, int index) {
        String groupName = "groupName_" + index;
        if (groupName.length() > 255) {
            groupName = groupName.substring(0, 255);
        }
        obj.setGroupName(groupName);
    }
    
    public void TdUserinfoDataOnDemand.setHasVerified(TdUserinfo obj, int index) {
        Boolean hasVerified = Boolean.TRUE;
        obj.setHasVerified(hasVerified);
    }
    
    public void TdUserinfoDataOnDemand.setLoginPassword(TdUserinfo obj, int index) {
        String loginPassword = "loginPassword_" + index;
        if (loginPassword.length() > 50) {
            loginPassword = loginPassword.substring(0, 50);
        }
        obj.setLoginPassword(loginPassword);
    }
    
    public void TdUserinfoDataOnDemand.setPasswordNonExpired(TdUserinfo obj, int index) {
        Boolean passwordNonExpired = Boolean.TRUE;
        obj.setPasswordNonExpired(passwordNonExpired);
    }
    
    public void TdUserinfoDataOnDemand.setPreferName(TdUserinfo obj, int index) {
        String preferName = "preferName_" + index;
        if (preferName.length() > 20) {
            preferName = preferName.substring(0, 20);
        }
        obj.setPreferName(preferName);
    }
    
    public void TdUserinfoDataOnDemand.setSpecial(TdUserinfo obj, int index) {
        String special = "special_" + index;
        if (special.length() > 255) {
            special = special.substring(0, 255);
        }
        obj.setSpecial(special);
    }
    
    public TdUserinfo TdUserinfoDataOnDemand.getSpecificTdUserinfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        TdUserinfo obj = data.get(index);
        Integer id = obj.getId();
        return tdUserinfoRepository.findOne(id);
    }
    
    public TdUserinfo TdUserinfoDataOnDemand.getRandomTdUserinfo() {
        init();
        TdUserinfo obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getId();
        return tdUserinfoRepository.findOne(id);
    }
    
    public boolean TdUserinfoDataOnDemand.modifyTdUserinfo(TdUserinfo obj) {
        return false;
    }
    
    public void TdUserinfoDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = tdUserinfoRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'TdUserinfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<TdUserinfo>();
        for (int i = 0; i < 10; i++) {
            TdUserinfo obj = getNewTransientTdUserinfo(i);
            try {
                tdUserinfoRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            tdUserinfoRepository.flush();
            data.add(obj);
        }
    }
    
}
