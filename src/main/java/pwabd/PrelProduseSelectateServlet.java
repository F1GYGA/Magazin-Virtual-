package pwabd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/PrelProduseSelectateServlet")
public class PrelProduseSelectateServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateResponse(request, response);
	}

    //trebuie verificat sa fie cumparat macar un produs, sa nu dea nullpointer exception


    public void generateResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

		ArrayList<Integer> pretProdus =  (ArrayList<Integer>) context.getAttribute("pretProdusAtribut");

        String[] stareCumparare = request.getParameterValues("stareCumparare");
        String[] cantitateProdus = request.getParameterValues("cantitateProdus");
        
        int sumaTotala = 0;
        
        for(int i = 0; i < stareCumparare.length; i++){
            
            sumaTotala+= Integer.parseInt(cantitateProdus[i])*pretProdus.get(Integer.parseInt(stareCumparare[i]));
            
        }

        session.setAttribute("sumaTotala", sumaTotala);
        session.setAttribute("cantitateProdus", cantitateProdus);
        session.setAttribute("stareCumparare", stareCumparare);
        response.sendRedirect("infoClient.html");
    }
}
