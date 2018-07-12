package it.esempio;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.service.ejb.AnotherEjb;
import com.test.singleton.MySingleton;



@WebServlet("/singleton")
public class MyServlet2 extends HttpServlet{

	@EJB
	MySingleton singletonEjb;
	
	@EJB
	MySingleton singletonEjb2;
	
	@EJB
	AnotherEjb anotherEjb;
		
	private static final long serialVersionUID = -8738195627738426139L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        singletonEjb.setStatus("Stato1");
        System.out.println("Ho dato a status questo valore: " + singletonEjb.getStatus());
                       
        singletonEjb.setStatus("Stato2");
        System.out.println("Ora gli ho dato quest'altro valore, sempre su singletonEjb: " + singletonEjb.getStatus());
        
        singletonEjb2.setStatus("StatoEjb2");
        System.out.println("Ora ho modificato singletonEjb2 e ora chiamo getStatus su singletonEjb e ti faccio vedere che un singleton è un singleton: " + singletonEjb.getStatus());
        
        anotherEjb.setStatus("Running...");
        System.out.println(anotherEjb.getStatus());



	}
	

	
	
	

}
