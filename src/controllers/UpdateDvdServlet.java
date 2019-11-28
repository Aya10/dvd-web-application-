package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.myDAO;
import models.Dvd;

@WebServlet("/UpdateDvdServlet")
public class UpdateDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateDvdServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			int id = Integer.valueOf(request.getParameter("id"));
			myDAO dao = new myDAO();
			try {
				Dvd dvd = dao.getStringDvd(id);
				request.setAttribute("dvd", dvd);

				request.getRequestDispatcher("update.jsp").forward(request, response);
			} catch (SQLException e) {
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
			String title = request.getParameter("title");
			String name = request.getParameter("name");
			String genre = request.getParameter("genre");
			int year = Integer.valueOf(request.getParameter("year"));

			Dvd dvd = new Dvd(id, title, genre, year);

			// mydao object
			myDAO dao = new myDAO();
			// using try catxh coz danger
			try {
				dao.updateDvd(dvd);
				response.sendRedirect("./DvdServlet");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./DvdServlet");
		}
	}

}
