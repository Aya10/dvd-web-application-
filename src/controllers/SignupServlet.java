package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.myDAO;
import models.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignupServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// forward to sign up page
		request.getRequestDispatcher("signup.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String pw2 = request.getParameter("password2");
		String apiKey = getAlphaNumericString(9);
		
		User u = new User(un, pw, apiKey);
		myDAO dao = new myDAO();
		try {
			dao.insertUser(u);
			response.sendRedirect("./DvdServlet");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// function to generate a random string of length a
	public String getAlphaNumericString(int n)

	{
		//choose a character random from this string 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWZYZ0123456789abcdefghijklmnopqrstuvwxyz";
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);
		
		for(int i = 0; i < n; i++) {
			//generate a random number between 0 to AlphaNumericString variable length
			int index = (int)(AlphaNumericString.length() *Math.random());
			//add character one by one to the end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}
}
