package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.myDAO;
import models.Dvd;

@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	myDAO dao = new myDAO();

	public ApiServlet() {
		super();

	}

	// params in URL
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setHeader("Content-Type", "application/json");
		try {
			if (dao.checkKey(request.getParameter("apiKey"))) {

				writer.write(gson.toJson(dao.getDVDs()));
			} else {
				writer.write("invalid api key.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// params in request body
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String dvd = request.getParameter("dvd");
		Dvd d = gson.fromJson(dvd, Dvd.class);
		try {
			if (dao.checkKey(request.getParameter("apiKey"))) {
				dao.insertDvd(d);
			} else {
				writer.write("invalid api key.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	// Params in url
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String dvd = request.getParameter("dvd");
		Dvd d = gson.fromJson(dvd, Dvd.class);
		try {
			if (dao.checkKey(request.getParameter("apiKey"))) {
				dao.updateDvd(d);
			} else {
				writer.write("invalid api key.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	// params in URL
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int id = Integer.valueOf(request.getParameter("id"));
		Dvd dvd = new Dvd(id, null, null, 8);

		try {
			if (dao.checkKey(request.getParameter("apiKey"))) {
				dao.deleteDvd(dvd);
			} else {
				writer.write("invalid api key.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
