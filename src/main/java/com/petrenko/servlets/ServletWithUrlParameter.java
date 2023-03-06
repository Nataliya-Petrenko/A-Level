package com.petrenko.servlets;

import com.petrenko.model.Car;
import com.petrenko.model.Type;
import com.petrenko.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ServletWithUrlParameter", value = "/src/main/java/com/petrenko/servlets/ServletWithUrlParameter")
public class ServletWithUrlParameter extends HttpServlet {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm:ss");
    private final CarService carService = CarService.getInstance();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseBody = response.getWriter();
        response.setContentType("text/html");

        responseBody.println("<h2 align=\"center\">Add \"?type=car\" or \"?type=truck\" at the end of url</h2>");

        String clientType = request.getParameter("type");
        Type type;
        if (clientType != null && (clientType.equals("car") || clientType.equals("truck"))) {
            if (clientType.equals("car")) {
                type = Type.CAR;
            } else {
                type = Type.TRUCK;
            }

            Car car = carService.create(type);
            LocalDateTime localDateTime = LocalDateTime.now();
            responseBody.println("<h3 align=\"center\">New car:</h3>");
            responseBody.println(String.format(("<h4 align=\"center\">%s: %s</h4>"),
                    localDateTime.format(formatter), car));

            responseBody.println("<h3 align=\"center\">All cars:</h3>");
            List<Car> cars = carService.getAll();
            cars.forEach(responseBody::println);
        }

    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }


}
