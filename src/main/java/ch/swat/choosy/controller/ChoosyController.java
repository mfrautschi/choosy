package ch.swat.choosy.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ChoosyController {
    private final Logger logger = LoggerFactory.getLogger(ChoosyController.class);

    @GetMapping(path="/")
    public void index(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        logger.debug("GET Request on index");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/team/");
        dispatcher.forward(request, response);
    }
}
