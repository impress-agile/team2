package rodeo.agile.impress.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ZaikoServletTest {

    private ZaikoServlet servlet;
    private ServletContext context;
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    
    @Before
    public void setup() {
    	this.context = mock(ServletContext.class);
    	when(context.getRealPath("WEB-INF/pos.db")).thenReturn("src/test/pos.db");
    	this.servlet = new ZaikoServlet() {
			private static final long serialVersionUID = 1L;
			public ServletContext getServletContext() {
        		return context;
        	}
        };
    	
    }
	
    @Test
    public void testGetAccessShouldBeForwardedToInputPage() throws ServletException, IOException {
        this.servlet.doGet(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/stocks/input_zaiko.jsp"));
    }

    @Test
    public void testPostAccessShouldBeForwardedToSuccessPageIfInputValuesAreValid() throws ServletException, IOException {
    	this.request.addParameter("id", "ValidName");
    	this.request.addParameter("suryo", "5");

        this.servlet.doPost(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/stocks/success.jsp"));
    } 
    
    @Test
    public void testPostAccessShouldBeForwaredToInputPageIfInputValuesAreNotValid() throws ServletException, IOException {
    	this.request.addParameter("id", "ValidName");
    	this.request.addParameter("suryo", "InvalidNumber");

        this.servlet.doPost(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/stocks/input_zaiko.jsp"));    	
    }
}
