package com.codegym;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DiscountServlet", urlPatterns = {"/display-discount"})
public class DiscountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double discountPercent = Double.parseDouble(request.getParameter("discount"));

        double discountAmount = price * discountPercent * 0.01;
        double discountPrice = price - discountAmount;

        request.setAttribute("description", description);
        request.setAttribute("price", price);
        request.setAttribute("discountPercent", discountPercent);
        request.setAttribute("discountAmount", discountAmount);
        request.setAttribute("discountPrice", discountPrice);

        request.getRequestDispatcher("display-discount.jsp").forward(request, response);
    }
}