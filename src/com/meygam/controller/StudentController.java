package com.meygam.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meygam.model.Student;
import com.meygam.repository.StudentRepository;

/**
 * Servlet implementation class StudentController
 */

@SuppressWarnings("serial")
public class StudentController extends HttpServlet {
	private StudentRepository studentRepository;

	private static String STUDENT_SIGNUP = "content/signup.jsp";
	private static String STUDENT_LOGIN = "content/login.jsp";
	private static String LOGIN_SUCCESS = "content/success.jsp";
	private static String LOGIN_FAILURE = "content/failure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		studentRepository = new StudentRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("signupStudent")) {
			forward = STUDENT_SIGNUP;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageName = request.getParameter("pageName");
		String forward = "";

		if (studentRepository != null) {
			if (pageName.equals("signup")) {
				if (studentRepository.findByUserName(request
						.getParameter("userName"))) {
					request.setAttribute("userNameExists", true);
					forward = STUDENT_SIGNUP;
					RequestDispatcher view = request
							.getRequestDispatcher(forward);
					view.forward(request, response);
					return;
				}

				studentRepository.save(request.getParameter("userName"),
						request.getParameter("password"),
						request.getParameter("firstName"),
						request.getParameter("lastName"),
						request.getParameter("dateOfBirth"),
						request.getParameter("emailAddress"));
				forward = STUDENT_LOGIN;
			} else if (pageName.equals("login")) {
				boolean result = studentRepository.findByLogin(
						request.getParameter("userName"),
						request.getParameter("password"));
				if (result == true) {
					forward = LOGIN_SUCCESS;
				} else {
					forward = LOGIN_FAILURE;
				}
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
