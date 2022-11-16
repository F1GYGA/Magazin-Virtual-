package pwabd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/afisareProduse")
public class ListaProduseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

	public void generateResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{

		ServletContext context = getServletContext();

		
		ArrayList<String> numeProdus = (ArrayList<String>) context.getAttribute("numeProdusAtribut");
		ArrayList<String> descriereProdus = (ArrayList<String>) context.getAttribute("descriereProdusAtribut");
		ArrayList<Integer> pretProdus =  (ArrayList<Integer>) context.getAttribute("pretProdusAtribut");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// generare de raspuns html
		out.println("<HTML>");
		out.println("<style> table, th, td { border:1px solid black;}</style>");
		out.println("<HEAD>");
		out.println("<TITLE>Catalog de produse</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<p> Selectati produsul dorit din lista </p>");

		out.println("<form ACTION='PrelProduseSelectateServlet' method='post'>");
		out.println("<table>");

		out.println("<tr>");
		out.println("<th>  Denumire produs </th>");
		out.println("<th>  Producator/autor </th>");
		out.println("<th>  Pret produs </th>");
		out.println("<th>  Cantitate </th>");
		out.println("<th>  Add to cart </th>");
		out.println("</tr>");

		for(int i = 0; i < numeProdus.size(); i++){
		out.println("<tr name="+numeProdus.get(i)+">");
		out.println("<td>");
		out.println(numeProdus.get(i));
		out.println("</td>");
		out.println("<td>");
		out.println(descriereProdus.get(i));
		out.println("</td>");	
		out.println("<td>");
		out.println(pretProdus.get(i)+" euro");
		out.println("</td>");
		out.println("<td>");
		out.println("<select name='cantitateProdus'>");
		out.println("<option value='1' selected> 1");
		out.println("<option value='2' > 2");
		out.println("<option value='3' > 3");
		out.println("<option value='4' > 4");
		out.println("<option value='5' > 5");
		out.println("</select>");
		out.println("</td>");
		out.println("<td>");
		out.println(" <input name='stareCumparare' type='checkbox' value="+i+">");
		out.println("</td>");	
		out.println("</tr>");
		}

		out.println("</table>");
		out.println("<input TYPE='submit' value='Cumparare'>");
		out.println("</form>");
		out.println("</BODY>");
		out.println("</HTML>");
		
	}
}
