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

@WebServlet("/insertNewDvdServlet")
public class insertNewDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public insertNewDvdServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		myDAO dao = new myDAO();

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			int year = Integer.valueOf(request.getParameter("year"));
			try {
				dao.insertDvd(new Dvd(0, title, genre, year));

				// redirect back to home page on successful insert
				response.sendRedirect("./DvdServlet");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			response.sendRedirect("./DvdServlet");
		}

	}
}
