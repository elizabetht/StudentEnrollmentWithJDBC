package com.student.controller;

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

import com.student.dao.StudentDao;
import com.student.model.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private static String STUDENT_SIGNUP = "signup.jsp";
	private static String STUDENT_LOGIN = "login.jsp";
	private static String LOGIN_SUCCESS = "success.jsp";
	private static String LOGIN_FAILURE = "failure.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();      
        studentDao = new StudentDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("signupStudent")) {
			forward = STUDENT_SIGNUP;			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageName = request.getParameter("pageName");
		String forward = "";		
		
		if(pageName.equals("signup")) {
			Student student = new Student();
			student.setUserName(request.getParameter("userName"));
			
			if (studentDao.checkUserName(request.getParameter("userName"))) {
				request.setAttribute("userNameExists", true);
				forward = STUDENT_SIGNUP;
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
				return;
			}
			student.setFirstName(request.getParameter("firstName"));
			student.setLastName(request.getParameter("lastName"));
			student.setPassword(request.getParameter("password"));
			student.setEmailAddress(request.getParameter("emailAddress"));
		
			try {
				Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dateOfBirth"));
				student.setDateOfBirth(dateOfBirth);
			} catch (ParseException e) {			
				e.printStackTrace();
			}		
			studentDao.addStudent(student);
			forward = STUDENT_LOGIN;		
		} else if (pageName.equals("login")) {
			boolean result = studentDao.verifyStudent(request.getParameter("userName"), request.getParameter("password"));
			if (result == true) {
				forward = LOGIN_SUCCESS;
			} else {				
				forward = LOGIN_FAILURE;
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);		
		
	}

}
