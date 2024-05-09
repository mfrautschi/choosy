package ch.swat.choosy.service;

import ch.swat.choosy.data.Participant;
import ch.swat.choosy.bo.TeamVariant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ch.swat.choosy.data.Participant.*;

class TeamGenerationServiceTest {
    private TeamGenerationService teamGenerationService;
    private final Logger logger = LoggerFactory.getLogger(TeamGenerationServiceTest.class);

    @BeforeEach
    void beforeEach() {
        teamGenerationService = new TeamGenerationService();
    }

    @ParameterizedTest
    @EnumSource(Participant.class)
    void generateAllPossibleTeamsNoOneTeams(Participant participant) {
        TeamVariant variant = TeamVariant.valueOf(participant.names().split(";").length);
        Map<TeamVariant, Map<String, List<String>>> map = teamGenerationService.generatePossibleTeam(participant.names(), variant);
        Assertions.assertEquals(Collections.emptyMap(), map);
    }

    @ParameterizedTest
    @EnumSource(Participant.class)
    void generateAllPossibleTeamsEvenParticipantCount(Participant participant) {
        Map<TeamVariant, Map<String, List<String>>> map = teamGenerationService.generateAllPossibleTeams(participant.names());
        int length = participant.names().split(";").length;
        if (length % 2 == 0 && length != 2) {
            Assertions.assertNotEquals(Collections.emptyMap(), map);
            Assertions.assertTrue(map.containsKey(TeamVariant.T2ER));
        } else {
            Assertions.assertFalse(map.containsKey(TeamVariant.T2ER));
        }
    }

    @ParameterizedTest
    @EnumSource(Participant.class)
    void generateAllPossibleTeamsRowOfThreeParticipantCount(Participant participant) {
        Map<TeamVariant, Map<String, List<String>>> map = teamGenerationService.generateAllPossibleTeams(participant.names());
        int length = participant.names().split(";").length;
        if (length % 3 == 0 && length != 3) {
            Assertions.assertNotEquals(Collections.emptyMap(), map);
            Assertions.assertTrue(map.containsKey(TeamVariant.T3ER));
        } else {
            Assertions.assertFalse(map.containsKey(TeamVariant.T3ER));
        }
    }

    @Test
    void generateAllPossibleTeams() {
        Map<TeamVariant, Map<String, List<String>>> map = teamGenerationService.generateAllPossibleTeams(participants10.names());
        Assertions.assertNotEquals(Collections.emptyMap(), map);
        printMap(map);
    }

    private void printMap(Map<TeamVariant, Map<String, List<String>>> map) {
        for (TeamVariant variant : map.keySet()) {
            Map<String, List<String>> teams = map.get(variant);
            logger.info("Team Variant {}", variant.toString());
            for (String key : teams.keySet()) {
                logger.info("In Team {} are {}", key, teams.get(key));
            }
            logger.info("##################");
        }
    }

    @Test
    void generatePossible2erTeam() {
        Map<TeamVariant, Map<String, List<String>>> teams = teamGenerationService.generatePossibleTeam(participants10.names(), TeamVariant.T2ER);
        Assertions.assertNotEquals(Collections.emptyMap(), teams);
        printMap(teams);
    }

    @Test
    void generatePossible5erTeam() {
        Map<TeamVariant, Map<String, List<String>>> teams = teamGenerationService.generatePossibleTeam(participants10.names(), TeamVariant.T5ER);
        Assertions.assertNotEquals(Collections.emptyMap(), teams);
        printMap(teams);
    }

    @Test
    void generatePossibleTeamWrongTeamVariant() {
        Map<TeamVariant, Map<String, List<String>>> teams = teamGenerationService.generatePossibleTeam(participants10.names(), TeamVariant.T4ER);
        Assertions.assertEquals(Collections.emptyMap(), teams);
    }

    @Test
    void calculatePossibleTeamVariants10ER() {
        LinkedList<TeamVariant> estimatedResult = new LinkedList<>();
        estimatedResult.add(TeamVariant.T2ER);
        estimatedResult.add(TeamVariant.T5ER);
        Assertions.assertEquals(estimatedResult, teamGenerationService.calculatePossibleTeamVariants(participants10.names().split(";")));
    }

    @Test
    void calculatePossibleTeamVariantsNegativeTest() {
        Assertions.assertFalse(teamGenerationService.calculatePossibleTeamVariants(participants10.names().split(";")).contains(TeamVariant.T10ER));
    }

    @Test
    void testTeamVariantNull(){
        Assertions.assertNull(TeamVariant.valueOf(105879));
    }
}