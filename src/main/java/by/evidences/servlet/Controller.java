package by.evidences.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.evidences.dao.PersistException;
import by.evidences.domain.Blog;
import by.evidences.mysql.MySqlBlogDao;
import by.evidences.mysql.MySqlDaoFactory;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private final static String ERROR_PAGE = "/error.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String url = "/result.jsp";
		String action = request.getParameter("action");
		List<Blog> listBlog = null;
        if (action.equals("recive")) {
        	MySqlDaoFactory factory = new MySqlDaoFactory();
        	MySqlBlogDao daoBlog = factory.getBlogDao();
            listBlog = new ArrayList<Blog>();
            listBlog = daoBlog.getAll();
            daoBlog.close();
                    	
        }
        
        request.setAttribute("listBlog", listBlog);
        RequestDispatcher dispatcher = getServletContext()
		    .getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		} catch (PersistException e) {
			String error = e.getMessage();
			request.setAttribute("error", error);
	        RequestDispatcher dispatcher = getServletContext()
			    .getRequestDispatcher(ERROR_PAGE);
			    dispatcher.forward(request, response);
        }
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doGet(request, response);
	}
}
