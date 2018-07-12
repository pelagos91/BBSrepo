package it.esempio.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener: Ho finito!");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener: Inizializzo il contesto");

	}

}
