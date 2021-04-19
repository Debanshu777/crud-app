package com.Debanshu777.mayadata;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailsJDBC detailsDao;
	public void init() {
		detailsDao=new DetailsJDBC();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action=request.getServletPath();
		try {
			switch(action) {
			case "/insert":
				insertDetails(request,response);
				break;
			case "/search":
				searchDetails(request,response);
				break;
			case "/delete":
				deleteDetails(request,response);
				break;
			case "/list":
				selectDetails(request,response);
				break;	
			default:
				break;
			}
		}catch(SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	private void deleteDetails(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
		detailsDao.deleteDetails(Long.parseLong(request.getParameter("slNo")));
	}
	
	private void selectDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{  
		String detailsJson=detailsDao.selectDetails();
		request.setAttribute("detailsJson", detailsJson);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(detailsJson);
	    response.getWriter().flush();
	}
	
	private void insertDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		DetailsPojo model=new DetailsPojo();
		try {
		model.setSlNo(Integer.parseInt(request.getParameter("slNo")));
	
		model.setMName(request.getParameter("MName"));
		model.setNPeople(Integer.parseInt(request.getParameter("NPeople")));
		model.setSDate(request.getParameter("SDate"));
		model.setSTime(request.getParameter("STime"));
		model.setETime(request.getParameter("ETime"));
		
		
		detailsDao.insertDetails(model);
		}catch(Exception ex) {
		 
		}
	}
	private void searchDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		
		String detailsJson=detailsDao.searchDetails(Integer.parseInt(request.getParameter("slNo")));
		request.setAttribute("detailsJson", detailsJson);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(detailsJson);
	    response.getWriter().flush();
	}


	}
