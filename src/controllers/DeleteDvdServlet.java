package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.myDAO;
import models.Dvd;

@WebServlet("/DeleteDvdServlet")
public class DeleteDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	myDAO dao = new myDAO();

	public DeleteDvdServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			int id = Integer.valueOf(request.getParameter("id"));

			try {
				dao.deleteDvd(new Dvd(id, null, null, 0));
				response.sendRedirect("./DvdServlet");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			response.sendRedirect("./DvdServlet");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			int id = Integer.valueOf(request.getParameter("id"));

			myDAO dao = new myDAO();

			try {
				dao.deleteDvd(new Dvd(id, null, null, 0));
				response.sendRedirect("./DvdServlet");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
