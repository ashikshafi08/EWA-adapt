import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VoiceAsstList")

public class VoiceAsstList extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, VoiceAsst> hm = new HashMap<String, VoiceAsst>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.vas);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Google"))
		   {
			 for(Map.Entry<String,VoiceAsst> entry : SaxParserDataStore.vas.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Google"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Google";
		   }
		   else if(CategoryName.equals("Amazon"))
		    {
			for(Map.Entry<String,VoiceAsst> entry : SaxParserDataStore.vas.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Amazon"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Amazon";
			}
			 else if(CategoryName.equals("Apple"))
		    {
			for(Map.Entry<String,VoiceAsst> entry : SaxParserDataStore.vas.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Apple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Apple";
			}
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Voice Assistant</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, VoiceAsst> entry : hm.entrySet())
		{
			VoiceAsst va = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+va.getName()+"</h3>");
			pw.print("<strong>$"+va.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/Voice Assistant/"+va.getImage()+"' alt='' /></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='vas'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form>");

			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='vas'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+va.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='vas'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");


			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		
	}
}
