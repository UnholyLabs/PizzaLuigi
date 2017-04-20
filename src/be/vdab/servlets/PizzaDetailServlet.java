package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.util.StringUtils;
import be.vdab.repositories.PizzaRepository;

@WebServlet("/pizzas/detail.htm")
public class PizzaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzaDetail.jsp";
	private final PizzaRepository pizzaRepository = new PizzaRepository();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if (StringUtils.isLong(idString)) {
			pizzaRepository.read(Long.parseLong(idString)).ifPresent(pizza -> request.setAttribute("pizza", pizza));
		} else {
			request.setAttribute("fout", "id niet correct");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}		
}
