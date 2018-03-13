package by.evidences.main;


import by.evidences.dao.PersistException;
import by.evidences.domain.Blog;
import by.evidences.mysql.MySqlBlogDao;
import by.evidences.mysql.MySqlDaoFactory;

import java.util.List;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws PersistException, IOException {
		
		String str = "2015-03-15";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dateTime = LocalDate.parse(str, formatter);
		
		//Files newFile = new Files("E:\\author.txt", "New File 3", 102, dateTime, 1);
		//Files newFile1 = new Files("E:\\author.txt", "New File 1.4", 102, dateTime, 1);
		Blog newBlog = new Blog("Title1", "djdf", 1);
		Blog newBlog1 = new Blog("djdf", "Title1.1", 1);
	    
        //MySqlDaoFactory daoFac = new MySqlDaoFactory();
        /*MySqlLocalitiesDao loc = new MySqlLocalitiesDao(conn);
        loc.persist(newLoc);
        MySqlDistrictsDao dist = new MySqlDistrictsDao(conn);
        dist.persist(newDist);
        MySqlDepartmentsDao dep = new MySqlDepartmentsDao(conn);
        dep.persist(newDep);*/
        /*blog.persist(newBlog);
        blog.persist(newBlog1);
        blog.persist(newBlog);
        blog.persist(newBlog1);*/
        
        
		MySqlDaoFactory factory = new MySqlDaoFactory(); 
        MySqlBlogDao daoBlog = factory.getBlogDao();
        
        //daoBlog.persist(newBlog);
        List<Blog> listBlog = new ArrayList<Blog>();
        listBlog = daoBlog.getByAuthorId(1);
        System.out.println(listBlog.get(58));
        daoBlog.close();
        /*List<Blog> listBlog = new ArrayList<Blog>();
        listBlog = daoBlog.getByAuthorId(1);
        //System.out.println(blog2.getBlogTitle());
        //factory.close();
        Blog lineBlog = listBlog.get(0);
        System.out.println(lineBlog.getBlogNews());
        daoBlog.close();
        MySqlBlogDao newDaoBlog = factory.getBlogDao();
        newDaoBlog.persist(newBlog);
        newDaoBlog.close();*/
	}

}
