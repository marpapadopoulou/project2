package com.example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.database.RegisterDao;
import com.example.product.Product;

@WebServlet("/RegisterProduct")
public class connect_servlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 //fetch product data from registration page
        String barcode = request.getParameter("barcode");
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        
        
    	//create database model
		RegisterDao regDao= new RegisterDao();
        
        //create product model
        Product product = new Product();
        product.setBarcode(barcode);
        product.setName(name);
        product.setColor(color);
        product.setDescription(description);
        
        
        if( regDao.validate(product)){
        	
        	//System.out.println(result);
        	response.sendRedirect("error.jsp");
        }
        else {
        	regDao.insert(product);
        	//fetch attributes
            request.setAttribute("barcode", request.getParameter("barcode"));
            request.setAttribute("name", request.getParameter("name") );
            request.setAttribute("color", request.getParameter("color"));
            request.setAttribute("description", request.getParameter("description") );
            
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        }
	}

}
