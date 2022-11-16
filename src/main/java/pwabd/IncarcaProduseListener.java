package pwabd;

import javax.servlet.*;
import java.io.*;
import java.util.ArrayList;

public class IncarcaProduseListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        String numeFisierCuProduse = context.getInitParameter("numeFisierCuProduse");

        ArrayList<String> numeProdus = new ArrayList<String>();
        ArrayList<String> descriereProdus = new ArrayList<String>();
        ArrayList<Integer> pretProdus = new ArrayList<Integer>();

        BufferedReader produseReader = null;

        try {
            InputStream is = context.getResourceAsStream(numeFisierCuProduse);
            produseReader = new BufferedReader(new InputStreamReader(is));

            String extract = null;
            java.util.Scanner s = null;
            while ((extract = produseReader.readLine()) != null) {
                s = new java.util.Scanner(extract);
                numeProdus.add(s.next());
                descriereProdus.add(s.next());
                pretProdus.add(Integer.parseInt(s.next()));
            }
           
            context.setAttribute("numeProdusAtribut", numeProdus);
            context.setAttribute("descriereProdusAtribut", descriereProdus);
            context.setAttribute("pretProdusAtribut", pretProdus);
        } catch (Exception e) {
            System.out.println("A avut loc o problema la incacare");
            numeProdus = null; 
            descriereProdus = null;
            pretProdus = null;
        } finally {
            if (produseReader != null) {
                try {
                    produseReader.close();

                } catch (IOException ioe) {
                }
            }

        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        context.setAttribute("numeProdusAtribut", null);
        context.setAttribute("descriereProdusAtribut", null);
        context.setAttribute("pretProdusAtribut", null);

    }

}
