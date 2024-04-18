package ch.swat.choosy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CivController {
    private final Logger logger = LoggerFactory.getLogger(CivController.class);

    @GetMapping(path="/civ")
    public String initialCiv(){
        logger.debug("GET Request received - initialCiv");
        return "civForm";
    }
}
