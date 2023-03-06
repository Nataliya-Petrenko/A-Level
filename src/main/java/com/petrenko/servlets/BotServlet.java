package com.petrenko.servlets;

import com.petrenko.model.Bot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BotServlet", value = "/bot")
public class BotServlet extends HttpServlet {
    private Bot bot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        bot = new Bot(1, "Amigo", "228274635");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");

        // здесь мы уже начинаем работать с адресной строкой. в ссылке <i>"bot?action=update"</i>
        // после вопросительного знака идут пары ключ-значение, разделённые знаком &. у нас есть пара action=update.
        // Вызываем значение (параметр) по ключу action, у нас это update и заносим результат в String.
        String action = request.getParameter("action");

        // загодя вносим в запрос атрибут, при исполнении сервлета он будет отправлен в целевую строку. атрибут вносится
        // вместе с ключом. в нашем случае, мы вносим объект bot со всеми его полями как атрибут и задаём ключ "bot",
        // по которому мы потом вызовем данные объекта в jsp-странице
        request.setAttribute("bot", bot);

        switch (action == null ? "info" : action) {
            /// если параметр имеет значение update (action=update), мы отправляемся на строку /update.jsp, где будем
            // изменять данные бота (и отправляем туда атрибут bot)
            case "update" -> request.getRequestDispatcher("/update.jsp").forward(request, response);
            // если параметр пустой (action == null ? "info"), отправляемся на страницу bot.jsp, где мы увидим данные
            case "info" -> request.getRequestDispatcher("/bot.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doPost");

        // правилом хорошего кода будет задать кодировку UTF-8 — в метод могут приходить параметры, скажем, на кириллице
        // (опять же, мы можем дать нашему боту кириллическое имя)
        request.setCharacterEncoding("UTF-8");

        // извлекаем значение параметра action и сохраняем в String, как мы это делали в методе doGet()
        String action = request.getParameter("action");

        // если action=submit, назначаем нашему боту новые значения, которые мы получили в метод. это делается также,
        // извлекая параметры. у каждого параметра из формы есть своё имя, по этому имени мы извлекаем значение
        // (например, в разбираемой выше строчке формы update.jsp name="id" value=${bot.id} мы задаём нашему боту
        // новый id, извлекая его в строчке bot.setId(Integer.parseInt(request.getParameter("id")));
        if ("submit".equals(action)) {
            bot.setId(Integer.parseInt(request.getParameter("id")));
            bot.setName(request.getParameter("name"));
            bot.setSerial(request.getParameter("serial"));
        }

        // опять запихиваем объект bot в атрибут и возвращаемся на страницу /bot.jsp, где наблюдаем изменения
        request.setAttribute("bot", bot);
        request.getRequestDispatcher("/bot.jsp").forward(request, response);
    }
}
