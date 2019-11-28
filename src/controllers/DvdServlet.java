package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.myDAO;
import models.Dvd;

@WebServlet("/DvdServlet")
public class DvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DvdServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// session.setAttribute("loggedin", true);
		myDAO dao = new myDAO();

		try {
			System.out.println(dao.ValidateUserInfo("ayah", "1234"));
			ArrayList<Dvd> allDvds = dao.getDVDs();
			// the following line passes the array to the view
			request.setAttribute("dvds", allDvds);

			request.getRequestDispatcher("home2.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
