package pwabd;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/GenerareChitantaServlet")
public class GenerareChitantaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

    public void generateResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        //preluare date din infoClient.html
        String nume = request.getParameter("campNume");
        String prenume = request.getParameter("campPrenume");
        String oras = request.getParameter("campOras");
        String adresa = request.getParameter("campAdresa");
        String modalitatDePlata = request.getParameter("modalitateDePlata");

        //preluare date din context
        ServletContext context = getServletContext();
        ArrayList<String> numeProdus = (ArrayList<String>) context.getAttribute("numeProdusAtribut");
		ArrayList<String> descriereProdus = (ArrayList<String>) context.getAttribute("descriereProdusAtribut");
		ArrayList<Integer> pretProdus =  (ArrayList<Integer>) context.getAttribute("pretProdusAtribut");

        //preluare date din sesiune
        HttpSession session = request.getSession();
        int sumaTotala = (int) session.getAttribute("sumaTotala");
        String[] cantitateProdus = (String[]) session.getAttribute("cantitateProdus");
        String[] stareCumparare = (String[]) session.getAttribute("stareCumparare");

        response.setContentType("text/html");
		PrintWriter out = response.getWriter();

        out.println("<HTML>");
        out.println("<style> table, th, td { border:1px solid black;}</style>");
		out.println("<HEAD>");
		out.println("<TITLE>Rezumatul comenzii</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");


        out.println("<H2> Produse achizitionate </H2>");
        out.println("<table>");

        out.println("<tr>");
		out.println("<th>  Denumire produs </th>");
		out.println("<th>  Producator/autor </th>");
		out.println("<th>  Pret produs </th>");
		out.println("<th>  Cantitate </th>");
		out.println("</tr>");

        for(int i = 0; i < stareCumparare.length; i++){
        out.println("<tr>");
        out.println("<td>");
        out.println(numeProdus.get(Integer.parseInt(stareCumparare[i])));
        out.println("</td>");
        out.println("<td>");
        out.println(descriereProdus.get(Integer.parseInt(stareCumparare[i])));
        out.println("</td>");
        out.println("<td>");
        out.println(pretProdus.get(Integer.parseInt(stareCumparare[i]))+" euro");
        out.println("</td>");
        out.println("<td>");
        out.println(cantitateProdus[i]);
        out.println("</td>");
        out.println("</tr>");
        }
        out.println("</table>");
        out.println("<h3> Suma totala platita: </h3>" + sumaTotala + " euro");
    
        out.println("<h2> Detalii client ");
        out.println("<table>");

        out.println("<tr>");
		out.println("<th>  Nume client </th>");
		out.println("<th>  Adresa </th>");
		out.println("<th>  Modalitate de plata  </th>");
		out.println("</tr>");

        out.println("<tr>");
        out.println("<td>");
        out.println(nume + " " + prenume);
        out.println("</td>");
        out.println("<td>");
        out.println(oras + "," + adresa);
        out.println("</td>");
        out.println("<td>");
        out.println(modalitatDePlata);
        out.println("</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</BODY>");
		out.println("</HTML>");




    }
    
}
