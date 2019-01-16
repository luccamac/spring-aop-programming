package com.ewolff.aspects;

import com.ewolff.repository.AnotherRepository;
import com.ewolff.repository.MyRepository;
import com.ewolff.service.MyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/system-configuration.xml")
public class AroundTracingAspectTest {

    @Autowired
    TracingAspect tracingAspect;

    @Autowired
    MyService myService;

    @Autowired
    MyRepository myRepository;

    @Autowired
    AnotherRepository anotherRepository;

    @Before
    public void setUp() {
        tracingAspect.resetCalled();
    }

    @Test
    public void tracingIsCalledForRepositories() {
        assertFalse(tracingAspect.isCalled());
        myRepository.doIt();
        assertTrue(tracingAspect.isCalled());
    }

    @Test
    public void tracingIsCalledForAnotherRepository() {
        assertFalse(tracingAspect.isCalled());
        anotherRepository.doSomething();
        assertTrue(tracingAspect.isCalled());
    }

    @Test
    public void tracingIsCalledForServices() {
        assertFalse(tracingAspect.isCalled());
        myService.doIt();
        assertTrue(tracingAspect.isCalled());
    }

}
