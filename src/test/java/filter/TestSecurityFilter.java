package filter;


import org.apache.cactus.FilterTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.client.authentication.BasicAuthentication;

import com.web.app.worldgames.web.filter.FilterToUser;

public class TestSecurityFilter extends FilterTestCase {

    private FilterToUser filter;
    private MockFilterChain mockChain;

    public TestSecurityFilter(String name) {
        super(name);
    }

    public void setUp(  ) {
        this.filter = new FilterToUser(  );
        this.mockChain = new MockFilterChain(  );
    }

    // this method runs on the client before testAuthenticatedUser(  )
    public void beginAuthenticatedUser(WebRequest webRequest) {
//    	webRequest.getAutomaticSession();
        webRequest.setRedirectorName("ServletRedirectorSecure");
        webRequest.setAuthentication(
                new BasicAuthentication("testuser", "testpassword"));
    }
    
    
    public void testBasicAuthentication()
    {
        assertEquals("testuser", request.getUserPrincipal().getName());
        assertEquals("testuser", request.getRemoteUser());
        assertTrue("User not in 'test' role", request.isUserInRole("test"));
    }

    // this method runs on the server
//    public void testAuthenticatedUser(  ) throws Exception {
//        this.mockChain.setExpectedInvocation(true);
//        this.filter.doFilter(this.request, this.response, this.mockChain);
//        this.mockChain.verify(  );
//    }
//
//    public void testNonAuthenticatedUser(  ) throws Exception {
//        this.mockChain.setExpectedInvocation(false);
//        this.filter.doFilter(this.request, this.response, this.mockChain);
//        this.mockChain.verify(  );
//    }
}