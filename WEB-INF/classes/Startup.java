import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Startup")


public class Startup extends HttpServlet
{

	public void init() throws ServletException
    {
		SaxParserDataStore.addHashmap();

    }
}
