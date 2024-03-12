package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

class TeamGenerationServiceTest {
    static String participants2 = "Bruno;Josef";
    static String participants3 = "Bruno;Josef;Alina";
    static String participants4 = "Bruno;Josef;Alina;Michelle";
    static String participants5 = "Bruno;Josef;Alina;Michelle;Tom";
    static String participants6 = "Bruno;Josef;Alina;Michelle;Tom;Luca";
    static String participants7 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena";
    static String participants8 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina";
    static String participants9 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard";
    static String participants10 = "Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard;Marco";
    private TeamGenerationService teamGenerationService;

    @BeforeEach
    void beforeEach() {
        teamGenerationService = new TeamGenerationService();
    }

    @Test
    void generateAllPossibleTeams() {
        Map<TeamVariant, Map<String, String>> map = teamGenerationService.generateAllPossibleTeams(participants10);
        Assertions.assertNotEquals(Collections.emptyMap(), map);
        printMap(map);
    }

    private void printMap(Map<TeamVariant, Map<String, String>> map) {
        for (TeamVariant variant : map.keySet()) {
            Map<String, String> teams = map.get(variant);
            System.out.println("Team Variant " + variant.toString());
            for (String key : teams.keySet()) {
                System.out.println("In Team " + key + " are " + teams.get(key));
            }
            System.out.println("##################");
        }
    }

    @Test
    void generatePossible2erTeam() {
        Map<TeamVariant, Map<String, String>> teams = teamGenerationService.generatePossibleTeam(participants10, TeamVariant.T2ER);
        Assertions.assertNotEquals(Collections.emptyMap(), teams);
        printMap(teams);
    }

    @Test
    void generatePossible5erTeam() {
        Map<TeamVariant, Map<String, String>> teams = teamGenerationService.generatePossibleTeam(participants10, TeamVariant.T5ER);
        Assertions.assertNotEquals(Collections.emptyMap(), teams);
        printMap(teams);
    }

    @Test
    void generatePossibleTeamWrongTeamVariant() {
        Map<TeamVariant, Map<String, String>> teams = teamGenerationService.generatePossibleTeam(participants10, TeamVariant.T4ER);
        Assertions.assertEquals(Collections.emptyMap(), teams);
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