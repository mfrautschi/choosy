package ch.swat.choosy.controller;

import ch.swat.choosy.bo.TeamVariant;
import ch.swat.choosy.service.TeamGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/team")
public class TeamController {
    private final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamGenerationService teamGenerationService;

    public TeamController(TeamGenerationService teamGenerationService) {
        this.teamGenerationService = teamGenerationService;
    }

    @GetMapping(path="/")
    public String initialTeam(){
        logger.debug("GET Request received - initialTeam");
        return "homeForm";
    }

    @PostMapping(path="/generate")
    public String generateTeamVariation(final @RequestParam String participants, final @RequestParam String teamvariant, final Model model){
        logger.debug("POST Request received");
        Map<TeamVariant, Map<String, List<String>>> teams;
        if(teamvariant.equals("TALL")){
            teams = teamGenerationService.generateAllPossibleTeams(participants);
        } else {
            teams = teamGenerationService.generatePossibleTeam(participants, TeamVariant.valueOf(teamvariant));
        }
        teams.forEach((key, value) -> logger.debug("{}:{}", key, value));
        model.addAttribute("teams", teams);
        return "generationResult";
    }
}