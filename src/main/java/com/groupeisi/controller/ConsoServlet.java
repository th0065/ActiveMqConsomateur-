package com.groupeisi.controller;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupeisi.consommateur.ConsImpl;
import com.groupeisi.consommateur.Icons;

/**
 * Servlet implementation class ConsoServlet
 */
@WebServlet(name="conso",value="/conso")
public class ConsoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public Icons icons;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String objet = request.getParameter("objet");
		if(objet!=null ) {
			String message ="";
			ConsImpl cons = null;
			try {
				 cons = new ConsImpl();
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			 message =	cons.getMessage(objet);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(message!=null) {
			 request.setAttribute("message", message);
			 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); 
			
		}
		if(message==null) {
			 request.setAttribute("message", "Echec de recuperation de message !!!");
			 this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); 
			
		}
		}
		
		}

}
