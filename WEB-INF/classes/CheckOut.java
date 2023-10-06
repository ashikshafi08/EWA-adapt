import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		utility.printHtml("header.html");
		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo' style='margin-top: 35px; margin-right: 281px'>");
		pw.print("</td></tr>");
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
	    pw.print("Customer Address</td>");
		pw.print("<td><input type='text' name='userAddress'>");
		//
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
		pw.print("<form action='/action_page.php'>");
		// pw.print("<label for='delivery'>Choose your preferance:</label></td>");
		pw.print("Choose your preferance:</td>");
		pw.print("<td><select name='delivery' id='delivery'>"+
		"<option value='pickup'>Pick Up from One of our Store</option>"+
		"<option value='athome'>Home Delivery</option>"+
		"</select>");
		pw.print("<br><br>");
		pw.print("</td></tr>");

		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
		pw.print("<form action='/action_page.php'>");
		// pw.print("<label for='delivery'>Choose your nearby:</label></td>");
		pw.print("Store ID:</td>");
		pw.print("<td><select name='delivery' id='delivery'>"+
		"<option value=''>6414 N RIDGE BLVD CHICAGO IL 60626-60654 USA</option>"+
		"<option value=''>3101 N CENTRAL PARK AVE CHICAGO IL 60618-6604 USA</option>"+
		"<option value=''>5900 S PEORIA ST CHICAGO IL 60621-2126 USA</option>"+
		"<option value=''>3256 W DIVERSEY AVE CHICAGO IL 60647-1568 USA</option>"+
		"<option value=''>PO BOX 7683 CHICAGO IL 60680-7616 USA</option>"+
		"<option value=''>5256 W FULTON ST CHICAGO IL 60644-2457 USA</option>"+
		"<option value=''>1837 N ALBANY AVE CHICAGO IL 60647-7363 USA</option>"+
		"<option value=''>800 S WELLS ST CHICAGO IL 60607-4573 USA</option>"+
		"<option value=''>2000 W MONTROSE AVE CHICAGO IL 60618-1748 USA</option>"+
		"</select>");
		pw.print("<br><br>");
		pw.print("</td></tr>");


		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
     	pw.print("Street</td>");
		pw.print("<td><input type='text' name='street'>");
		pw.print("</td></tr>");
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
     	pw.print("City</td>");
		pw.print("<td><input type='text' name='city'>");
		pw.print("</td></tr>");
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
     	pw.print("State</td>");
		pw.print("<td><input type='text' name='state'>");
		pw.print("</td></tr>");
		pw.print("<tr><td style='font-family: ui-serif; font-size: initial'>");
     	pw.print("Zip-Code</td>");
		pw.print("<td><input type='text' name='zipcode'>");
		pw.print("</td></tr>");


        pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy' style='margin-top: 60px; margin-left: 130px'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
