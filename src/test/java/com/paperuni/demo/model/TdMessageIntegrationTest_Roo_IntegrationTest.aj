// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdMessageDataOnDemand;
import com.paperuni.demo.model.TdMessageIntegrationTest;
import com.paperuni.demo.model.TdMessagePK;
import com.paperuni.demo.model.TdMessageRepository;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TdMessageIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TdMessageIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TdMessageIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TdMessageIntegrationTest: @Transactional;
    
    @Autowired
    TdMessageDataOnDemand TdMessageIntegrationTest.dod;
    
    @Autowired
    TdMessageRepository TdMessageIntegrationTest.tdMessageRepository;
    
    @Test
    public void TdMessageIntegrationTest.testCount() {
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", dod.getRandomTdMessage());
        long count = tdMessageRepository.count();
        Assert.assertTrue("Counter for 'TdMessage' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TdMessageIntegrationTest.testFind() {
        TdMessage obj = dod.getRandomTdMessage();
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", obj);
        TdMessagePK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to provide an identifier", id);
        obj = tdMessageRepository.findOne(id);
        Assert.assertNotNull("Find method for 'TdMessage' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'TdMessage' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TdMessageIntegrationTest.testFindAll() {
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", dod.getRandomTdMessage());
        long count = tdMessageRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'TdMessage', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<TdMessage> result = tdMessageRepository.findAll();
        Assert.assertNotNull("Find all method for 'TdMessage' illegally returned null", result);
        Assert.assertTrue("Find all method for 'TdMessage' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TdMessageIntegrationTest.testFindEntries() {
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", dod.getRandomTdMessage());
        long count = tdMessageRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<TdMessage> result = tdMessageRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'TdMessage' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'TdMessage' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TdMessageIntegrationTest.testSave() {
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", dod.getRandomTdMessage());
        TdMessage obj = dod.getNewTransientTdMessage(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to provide a new transient entity", obj);
        try {
            tdMessageRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        tdMessageRepository.flush();
        Assert.assertNotNull("Expected 'TdMessage' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TdMessageIntegrationTest.testDelete() {
        TdMessage obj = dod.getRandomTdMessage();
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to initialize correctly", obj);
        TdMessagePK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TdMessage' failed to provide an identifier", id);
        obj = tdMessageRepository.findOne(id);
        tdMessageRepository.delete(obj);
        tdMessageRepository.flush();
        Assert.assertNull("Failed to remove 'TdMessage' with identifier '" + id + "'", tdMessageRepository.findOne(id));
    }
    
}
