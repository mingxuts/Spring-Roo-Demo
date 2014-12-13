// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdUserinfoDataOnDemand;
import com.paperuni.demo.model.TdUserinfoIntegrationTest;
import com.paperuni.demo.model.TdUserinfoRepository;
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

privileged aspect TdUserinfoIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TdUserinfoIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TdUserinfoIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TdUserinfoIntegrationTest: @Transactional;
    
    @Autowired
    TdUserinfoDataOnDemand TdUserinfoIntegrationTest.dod;
    
    @Autowired
    TdUserinfoRepository TdUserinfoIntegrationTest.tdUserinfoRepository;
    
    @Test
    public void TdUserinfoIntegrationTest.testCount() {
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", dod.getRandomTdUserinfo());
        long count = tdUserinfoRepository.count();
        Assert.assertTrue("Counter for 'TdUserinfo' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TdUserinfoIntegrationTest.testFind() {
        TdUserinfo obj = dod.getRandomTdUserinfo();
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", obj);
        Integer id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to provide an identifier", id);
        obj = tdUserinfoRepository.findOne(id);
        Assert.assertNotNull("Find method for 'TdUserinfo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'TdUserinfo' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TdUserinfoIntegrationTest.testFindAll() {
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", dod.getRandomTdUserinfo());
        long count = tdUserinfoRepository.count();
        Assert.assertTrue("Too expensive to perform a find all test for 'TdUserinfo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<TdUserinfo> result = tdUserinfoRepository.findAll();
        Assert.assertNotNull("Find all method for 'TdUserinfo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'TdUserinfo' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TdUserinfoIntegrationTest.testFindEntries() {
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", dod.getRandomTdUserinfo());
        long count = tdUserinfoRepository.count();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<TdUserinfo> result = tdUserinfoRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
        Assert.assertNotNull("Find entries method for 'TdUserinfo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'TdUserinfo' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TdUserinfoIntegrationTest.testSave() {
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", dod.getRandomTdUserinfo());
        TdUserinfo obj = dod.getNewTransientTdUserinfo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'TdUserinfo' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'TdUserinfo' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TdUserinfoIntegrationTest.testDelete() {
        TdUserinfo obj = dod.getRandomTdUserinfo();
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to initialize correctly", obj);
        Integer id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TdUserinfo' failed to provide an identifier", id);
        obj = tdUserinfoRepository.findOne(id);
        tdUserinfoRepository.delete(obj);
        tdUserinfoRepository.flush();
        Assert.assertNull("Failed to remove 'TdUserinfo' with identifier '" + id + "'", tdUserinfoRepository.findOne(id));
    }
    
}
