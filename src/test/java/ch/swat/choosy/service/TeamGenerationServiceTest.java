package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

class TeamGenerationServiceTest {
    private TeamGenerationService teamGenerationService;
    static String participants2 = "Bruno;Josef";
    static String participants3 = "Bruno;Josef;Alina";
    static String participants4 = "Bruno;Josef;Alina;Michelle";
    static String participants5 = "Bruno;Josef;Alina;Michelle;Tom";
    static String participants6 = "Bruno;Josef;Alina;Michelle;Tom;Luca";
    static String participants7 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena";
    static String participants8 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina";
    static String participants9 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard";
    static String participants10 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard;Marco";

    @BeforeEach
    void beforeEach() {
        teamGenerationService = new TeamGenerationService();
    }

    @Test
    void generateAllPossibleTeams(){
        Assertions.assertEquals(Collections.emptyMap(), teamGenerationService.generateAllPossibleTeams(participants10));
    }

    @Test
    void generatePossibleTeam(){
        Assertions.assertEquals(Collections.emptyMap(), teamGenerationService.generatePossibleTeam(participants10, TeamVariant.T2ER));
    }

    @Test
    void calculatePossibleTeamVariants10ER() {
        LinkedList<TeamVariant> estimatedResult = new LinkedList<>();
        estimatedResult.add(TeamVariant.T2ER);
        estimatedResult.add(TeamVariant.T5ER);
        Assertions.assertEquals(estimatedResult, teamGenerationService.calculatePossibleTeamVariants(participants10.split(";")));
    }

    @Test
    void calculatePossibleTeamVariantsNegativeTest() {
        Assertions.assertFalse(teamGenerationService.calculatePossibleTeamVariants(participants10.split(";")).contains(TeamVariant.T10ER));
    }
}