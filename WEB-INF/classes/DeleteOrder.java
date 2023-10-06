import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/DeleteOrder")

public class DeleteOrder extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add product");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
//		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
//		pw.print("</h2><div class='entry'>");
//		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
//		if(utility.CartCount()>0)
/*		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("</tr>");
				total = total +oi.getPrice();
				i++;
			}
			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");	
			pw.print("<tr><th></th><th>Total</th><th>"+total+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			//pw.print(carousel.carouselfeature(utility));
			// pw.println(utility.getCustomerOrders());
			//pw.println("  SaxParserDataStore.phones :: " + SaxParserDataStore.phones);
			///for (OrderItem item: utility.getCustomerOrders()) {
				//pw.println(" ");
				//pw.println(" ");
				//pw.println(" ");	
		//		pw.println("Item name  ::: " + item.getName());
		//		if(SaxParserDataStore.phones.containsKey(item.getName())){
		//			pw.println("true");
		//
		//		} else{
		//	pw.print("false");
		//		}
		//		pw.println(" ");
		//		pw.println(" ");
		//		pw.println(" ");
		//	}
	//	}
		//else
		//{
		//	pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
	//	}
		pw.print("<h1 style='font-size: xx-large; width: max-content; margin-bottom: 800px; margin-top: -54px; margin-left: 155px;'>Order has been deleted successfully.<h1>");		
		utility.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
