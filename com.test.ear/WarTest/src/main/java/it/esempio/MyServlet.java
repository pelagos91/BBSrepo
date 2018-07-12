package it.esempio;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dto.UtenteDto;
import com.test.exceptions.*;
import com.test.service.UtenteService;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 3929384656548561931L;

	@EJB
	private UtenteService userService;
	
	
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Moio....");
	}


	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Inizializzo la servlet");
		
	}


	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtenteDto userDelete = null;

		try {
			Integer userId = Integer.parseInt(request.getParameter("userid"));
			userDelete = userService.deleteUser(userId);
			response.getWriter().println("Utente cancellato: " + userDelete.toString());
		} catch (UserNotFoundException e) {
			response.getWriter().println(e.getMessage());
			response.setStatus(404);
		}
		
	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		UtenteDto userPut = null;
		
		try {
		
			String keyToUpdate = keyIdentify(request, response, session);
			String toUpdate = request.getParameter(keyToUpdate);
			Integer userId = Integer.parseInt(request.getParameter("userid"));
			userPut = userService.updateUser(userId, keyToUpdate, toUpdate);
			response.getWriter().println("Utente modificato: " + userPut.toString());
		} catch (ValidationException e) {
			e.printStackTrace();
			response.setStatus(e.getErrorCode());
			response.getWriter().println("ERROR" + e.getErrorCode());
			response.getWriter().println(e.getMessage());
			
		} catch (UserNotFoundException e) {
			response.getWriter().println(e.getMessage());
			response.setStatus(404);			
		} catch (NumberFormatException e) {
			response.getWriter().println("ID errato");
			response.setStatus(400);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// getPreviousUser(session, response);

		storeUser(request, response, session);

		System.out.println("Id sessione da doPost: " + session.getId());

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// getPreviousUser(session, response);

		// storeUser(request, response, session);

		try {
			Integer userId = Integer.parseInt(request.getParameter("userid"));
			UtenteDto user3 = userService.findUser(userId);
			response.getWriter().println("Utente cercato: " + user3.toString());
		} catch (NumberFormatException e) {
			response.getWriter().println("ID errato");
			response.setStatus(400);
		} catch (UserNotFoundException e) {
			response.getWriter().println(e.getMessage());
			response.setStatus(404);
		}

		System.out.println("Id sessione da doGet: " + session.getId());

		// response.getWriter().println("This is a response example!");
	}

	private void storeUser(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {

		String nome;
		String cognome;
		UtenteDto user2 = null;

		try {
			nome = request.getParameter("nome");
			cognome = request.getParameter("cognome");

			user2 = userService.createUser(nome, cognome);

			session.setAttribute("Utente", user2);

			// Restituisco i parametri dell'istanza attraverso i metodi get
			// response.getWriter().println("Nome e cognome tramite get: " + user2.getNome()
			// + " " + user2.getCognome());

			// Restituisco i parametri dell'istanza attraverso il metodo toString
			response.getWriter().println("Nome e cognome tramite toString utenteDTO: " + user2.toString());
			response.setStatus(200);
		} catch (ValidationException e) {
			e.printStackTrace();
			response.setStatus(e.getErrorCode());
			response.getWriter().println("ERROR" + e.getErrorCode());

		}
	}
	
	
	private String keyIdentify(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ValidationException {
		String keyToUpdate="valoreInizialeKeyToUpdate";
		if(request.getParameter("nome") != null) {
			keyToUpdate = "nome";
			return keyToUpdate;
		} else if(request.getParameter("cognome") != null) {
			keyToUpdate = "cognome";
			return keyToUpdate;
		} else {
			throw new ValidationException("Dati in input NON validi!", 400);
		}
	}

	private void getPreviousUser(HttpSession session, HttpServletResponse response) throws IOException {

		if (!session.isNew() && (UtenteDto) session.getAttribute("Utente") != null) {

			UtenteDto user1 = (UtenteDto) session.getAttribute("Utente");
			// Restituisco i parametri dell'istanza attraverso i metodi get dell'oggetto
			// salvato in sessione
			response.getWriter().println("Nome e cognome dell'oggetto precedentemente salvato in sessione: "
					+ user1.getNome() + " " + user1.getCognome());

		}

	}

}
