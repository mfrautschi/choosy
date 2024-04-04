package ch.swat.choosy.controller;

import ch.swat.choosy.bo.TeamVariant;
import ch.swat.choosy.service.TeamGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TeamGenerationController {
    final TeamGenerationService teamGenerationService;
    private final Logger logger = LoggerFactory.getLogger(TeamGenerationController.class);

    public TeamGenerationController(TeamGenerationService teamGenerationService){
        this.teamGenerationService = teamGenerationService;
    }

    @GetMapping(path="/")
    public String initialTeamGeneration(){
        logger.debug("GET Request received");
        return "homeForm";
    }

    @PostMapping(path="/generate")
    public String generateTeamVaraiation(final @RequestParam String participants, final @RequestParam String teamvariant, final Model model){
        logger.debug("POST Request received");
        Map<TeamVariant,Map<String, String>> teams;
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
