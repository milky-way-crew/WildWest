package controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.web.HomeController;
import com.web.app.worldgames.web.HttpErrorController;

public class TestHomeController {
	private final static Logger log = Logger.getLogger(TestHomeController.class);
	

	 @Autowired
	    private ApplicationContext applicationContext;

	    private MockHttpServletRequest request;
	    private MockHttpServletResponse response;
	    private HandlerAdapter handlerAdapter;
	    private HomeController controller;

	@Before
	public void setUp() throws Exception {
		 request = new MockHttpServletRequest();
	       response = new MockHttpServletResponse();
	       handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
	       controller = new HomeController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHome() throws Exception {
		log.info("hhh");
		 request.setRequestURI("/home");
	       final ModelAndView mav = handlerAdapter.handle(request, response, controller);
	       assertEquals(mav.getViewName(), "view");
    
	}

}
