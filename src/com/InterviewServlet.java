package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/*      1. Настроить у себя Google Apps plugin.
        2. Разобраться с HTML.
        3. Создать проект «Анкета». Сделать возможность ввода
        пользователем его имени, фамилии, возраста и ответов на
        2-3 вопроса. Данные должны отправляться на сервер,
        который в ответ должен вернуть статистику по ответам в
        виде HTML документа.*/
public class InterviewServlet extends HttpServlet {

    private static HashMap<String, Integer> statistics = new HashMap<>();
    private static int numberOfAnswers;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for (int i = 1; i < 4; i++) {
            String value = req.getParameter("element_" + i);
            if (value != null) {
                int valueStat = (statistics.get("element_" + i + "_" + value) == null) ? 0 : statistics.get("element_" + i + "_" + value);
                valueStat++;
                statistics.put("element_" + i + "_" + value, valueStat);
            }
        }

        numberOfAnswers++;

        resp.sendRedirect("/result.jsp");

    }

    @Override
    public String toString() {
        String result = "";
        for (String key : statistics.keySet()) {
            result += key + ": " + statistics.get(key) + "\n";
        }

        result += "Number of answers = " + numberOfAnswers;

        return result;
    }
}
