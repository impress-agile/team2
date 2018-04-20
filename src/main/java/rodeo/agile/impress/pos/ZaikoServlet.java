package rodeo.agile.impress.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZaikoServlet extends HttpServlet {

    private static final long serialVersionUID = 6961400581681209885L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/stocks/input_zaiko.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String suryoString = request.getParameter("suryo");

        int suryo = 0;
        try {
        	suryo = Integer.parseInt(suryoString);

        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	ZaikoDao dao = new ZaikoDao(dbPath);

        	ZaikoLogic logic = new ZaikoLogic(dao);
        	logic.add(id, suryo);
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("id", id);
            request.setAttribute("suryo", suryoString);
            request.getRequestDispatcher("jsp/stocks/input_zaiko.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("jsp/stocks/success.jsp").forward(request, response);
    }

}
