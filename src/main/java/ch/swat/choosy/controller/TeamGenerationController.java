package ch.swat.choosy.controller;

import ch.swat.choosy.bo.TeamVariant;
import ch.swat.choosy.service.TeamGenerationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TeamGenerationController {
    TeamGenerationService teamGenerationService;

    @GetMapping(path="/")
    public String initialTeamGeneration(){
        return "homeForm";
    }

    @PostMapping(path="/generate")
    public String generateTeamVaraiation(@RequestParam String participants, @RequestParam String teamvariant, Model model){
        Map<String, String> teams;
        if(teamvariant.equals("TALL")){
            teams = teamGenerationService.generateAllPossibleTeams(participants);
        } else {
            teams = teamGenerationService.generatePossibleTeam(participants, TeamVariant.valueOf(teamvariant));
        }
        model.addAttribute("files", teams);
        return "generationResult";
    }
}
