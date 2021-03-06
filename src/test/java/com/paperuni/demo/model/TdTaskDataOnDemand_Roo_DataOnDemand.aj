// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdTask;
import com.paperuni.demo.model.TdTaskDataOnDemand;
import com.paperuni.demo.model.TdTaskRepository;
import com.paperuni.demo.model.TdUserinfoDataOnDemand;
import java.math.BigDecimal;
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

privileged aspect TdTaskDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TdTaskDataOnDemand: @Component;
    
    private Random TdTaskDataOnDemand.rnd = new SecureRandom();
    
    private List<TdTask> TdTaskDataOnDemand.data;
    
    @Autowired
    TdUserinfoDataOnDemand TdTaskDataOnDemand.tdUserinfoDataOnDemand;
    
    @Autowired
    TdTaskRepository TdTaskDataOnDemand.tdTaskRepository;
    
    public TdTask TdTaskDataOnDemand.getNewTransientTdTask(int index) {
        TdTask obj = new TdTask();
        setAllowAllSubject(obj, index);
        setAmountDue(obj, index);
        setChargeWriter(obj, index);
        setCommission(obj, index);
        setCoupon(obj, index);
        setCourseLevel(obj, index);
        setCreateDate(obj, index);
        setDeadLine(obj, index);
        setFeedBack(obj, index);
        setFeedBackDescription(obj, index);
        setFile(obj, index);
        setFileContentType(obj, index);
        setFileName(obj, index);
        setFileSize(obj, index);
        setFormat(obj, index);
        setFullPrice(obj, index);
        setIncludeFigure(obj, index);
        setNote(obj, index);
        setOrderId(obj, index);
        setReduced(obj, index);
        setReferencing(obj, index);
        setSourcesCount(obj, index);
        setStartDate(obj, index);
        setStatus(obj, index);
        setSubjectId(obj, index);
        setWordCount(obj, index);
        return obj;
    }
    
    public void TdTaskDataOnDemand.setAllowAllSubject(TdTask obj, int index) {
        Boolean allowAllSubject = Boolean.TRUE;
        obj.setAllowAllSubject(allowAllSubject);
    }
    
    public void TdTaskDataOnDemand.setAmountDue(TdTask obj, int index) {
        BigDecimal amountDue = BigDecimal.valueOf(index);
        if (amountDue.compareTo(new BigDecimal("999999.99")) == 1) {
            amountDue = new BigDecimal("999999.99");
        }
        obj.setAmountDue(amountDue);
    }
    
    public void TdTaskDataOnDemand.setChargeWriter(TdTask obj, int index) {
        BigDecimal chargeWriter = BigDecimal.valueOf(index);
        if (chargeWriter.compareTo(new BigDecimal("999999.99")) == 1) {
            chargeWriter = new BigDecimal("999999.99");
        }
        obj.setChargeWriter(chargeWriter);
    }
    
    public void TdTaskDataOnDemand.setCommission(TdTask obj, int index) {
        BigDecimal commission = BigDecimal.valueOf(index);
        if (commission.compareTo(new BigDecimal("999999.99")) == 1) {
            commission = new BigDecimal("999999.99");
        }
        obj.setCommission(commission);
    }
    
    public void TdTaskDataOnDemand.setCoupon(TdTask obj, int index) {
        String coupon = "coupon_" + index;
        if (coupon.length() > 35) {
            coupon = coupon.substring(0, 35);
        }
        obj.setCoupon(coupon);
    }
    
    public void TdTaskDataOnDemand.setCourseLevel(TdTask obj, int index) {
        String courseLevel = "courseLe_" + index;
        if (courseLevel.length() > 10) {
            courseLevel = courseLevel.substring(0, 10);
        }
        obj.setCourseLevel(courseLevel);
    }
    
    public void TdTaskDataOnDemand.setCreateDate(TdTask obj, int index) {
        Calendar createDate = Calendar.getInstance();
        obj.setCreateDate(createDate);
    }
    
    public void TdTaskDataOnDemand.setDeadLine(TdTask obj, int index) {
        Calendar deadLine = Calendar.getInstance();
        obj.setDeadLine(deadLine);
    }
    
    public void TdTaskDataOnDemand.setFeedBack(TdTask obj, int index) {
        String feedBack = "f" + index;
        if (feedBack.length() > 2) {
            feedBack = feedBack.substring(0, 2);
        }
        obj.setFeedBack(feedBack);
    }
    
    public void TdTaskDataOnDemand.setFeedBackDescription(TdTask obj, int index) {
        String feedBackDescription = "feedBackDescription_" + index;
        if (feedBackDescription.length() > 255) {
            feedBackDescription = feedBackDescription.substring(0, 255);
        }
        obj.setFeedBackDescription(feedBackDescription);
    }
    
    public void TdTaskDataOnDemand.setFile(TdTask obj, int index) {
        byte[] file = String.valueOf(index).getBytes();
        obj.setFile(file);
    }
    
    public void TdTaskDataOnDemand.setFileContentType(TdTask obj, int index) {
        String fileContentType = "fileContentType_" + index;
        if (fileContentType.length() > 50) {
            fileContentType = fileContentType.substring(0, 50);
        }
        obj.setFileContentType(fileContentType);
    }
    
    public void TdTaskDataOnDemand.setFileName(TdTask obj, int index) {
        String fileName = "fileName_" + index;
        if (fileName.length() > 70) {
            fileName = fileName.substring(0, 70);
        }
        obj.setFileName(fileName);
    }
    
    public void TdTaskDataOnDemand.setFileSize(TdTask obj, int index) {
        Integer fileSize = new Integer(index);
        obj.setFileSize(fileSize);
    }
    
    public void TdTaskDataOnDemand.setFormat(TdTask obj, int index) {
        String format = "format_" + index;
        if (format.length() > 30) {
            format = format.substring(0, 30);
        }
        obj.setFormat(format);
    }
    
    public void TdTaskDataOnDemand.setFullPrice(TdTask obj, int index) {
        BigDecimal fullPrice = BigDecimal.valueOf(index);
        if (fullPrice.compareTo(new BigDecimal("999999.99")) == 1) {
            fullPrice = new BigDecimal("999999.99");
        }
        obj.setFullPrice(fullPrice);
    }
    
    public void TdTaskDataOnDemand.setIncludeFigure(TdTask obj, int index) {
        String includeFigure = "includeFigure_" + index;
        if (includeFigure.length() > 30) {
            includeFigure = includeFigure.substring(0, 30);
        }
        obj.setIncludeFigure(includeFigure);
    }
    
    public void TdTaskDataOnDemand.setNote(TdTask obj, int index) {
        String note = "note_" + index;
        if (note.length() > 255) {
            note = note.substring(0, 255);
        }
        obj.setNote(note);
    }
    
    public void TdTaskDataOnDemand.setOrderId(TdTask obj, int index) {
        Integer orderId = new Integer(index);
        obj.setOrderId(orderId);
    }
    
    public void TdTaskDataOnDemand.setReduced(TdTask obj, int index) {
        BigDecimal reduced = BigDecimal.valueOf(index);
        if (reduced.compareTo(new BigDecimal("999999.99")) == 1) {
            reduced = new BigDecimal("999999.99");
        }
        obj.setReduced(reduced);
    }
    
    public void TdTaskDataOnDemand.setReferencing(TdTask obj, int index) {
        String referencing = "referencing_" + index;
        if (referencing.length() > 30) {
            referencing = referencing.substring(0, 30);
        }
        obj.setReferencing(referencing);
    }
    
    public void TdTaskDataOnDemand.setSourcesCount(TdTask obj, int index) {
        Short sourcesCount = new Integer(index).shortValue();
        obj.setSourcesCount(sourcesCount);
    }
    
    public void TdTaskDataOnDemand.setStartDate(TdTask obj, int index) {
        Calendar startDate = Calendar.getInstance();
        obj.setStartDate(startDate);
    }
    
    public void TdTaskDataOnDemand.setStatus(TdTask obj, int index) {
        String status = "status_" + index;
        if (status.length() > 88) {
            status = status.substring(0, 88);
        }
        obj.setStatus(status);
    }
    
    public void TdTaskDataOnDemand.setSubjectId(TdTask obj, int index) {
        Integer subjectId = new Integer(index);
        obj.setSubjectId(subjectId);
    }
    
    public void TdTaskDataOnDemand.setWordCount(TdTask obj, int index) {
        Integer wordCount = new Integer(index);
        obj.setWordCount(wordCount);
    }
    
    public TdTask TdTaskDataOnDemand.getSpecificTdTask(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        TdTask obj = data.get(index);
        Integer id = obj.getId();
        return tdTaskRepository.findOne(id);
    }
    
    public TdTask TdTaskDataOnDemand.getRandomTdTask() {
        init();
        TdTask obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getId();
        return tdTaskRepository.findOne(id);
    }
    
    public boolean TdTaskDataOnDemand.modifyTdTask(TdTask obj) {
        return false;
    }
    
    public void TdTaskDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = tdTaskRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'TdTask' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<TdTask>();
        for (int i = 0; i < 10; i++) {
            TdTask obj = getNewTransientTdTask(i);
            try {
                tdTaskRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            tdTaskRepository.flush();
            data.add(obj);
        }
    }
    
}
