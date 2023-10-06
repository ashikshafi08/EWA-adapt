import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String u=request.getParameter("usertype");
	
		Utilities utility = new Utilities(request,pw);
		String u=utility.usertype();
		System.out.println(u);
		//System.out.println(type(u));
		pw.println(u);
if(u!=null){
		if(u.equalsIgnoreCase("customer")){
		utility.printHtml("header.html");
		utility.printHtml("content.html");
		utility.printHtml("LeftNavBar.html");
		utility.printHtml("footer.html");
	}
	else if(u.equalsIgnoreCase("retailer")){
		utility.printHtml("header.html");
		utility.printHtml("contentstoremanager.html");
		utility.printHtml("LeftNavBar.html");
		utility.printHtml("footer.html");
	}
	else if(u.equalsIgnoreCase("manager")){
		utility.printHtml("header.html");
		utility.printHtml("contentsalesman.html");
		utility.printHtml("LeftNavBar.html");
		utility.printHtml("footer.html");
	}
}
	else
	{
		utility.printHtml("header.html");
		utility.printHtml("content.html");
		utility.printHtml("LeftNavBar.html");
		utility.printHtml("footer.html");
	
	}
				
	}

}
