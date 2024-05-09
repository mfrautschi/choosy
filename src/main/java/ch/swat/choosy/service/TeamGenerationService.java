package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class TeamGenerationService {
    public Map<TeamVariant, Map<String, String>> generateAllPossibleTeams(String participants) {

        Map<TeamVariant, Map<String, String>> assignedTeams = new EnumMap<>(TeamVariant.class);
        calculatePossibleTeamVariants(participants.split(";"))
                .stream()
                .parallel()
                .forEach(v -> assignedTeams.put(v, assignParticipantsToTeams(v, participants.split(";"))));

        return assignedTeams;
    }

    private Map<String, String> assignParticipantsToTeams(TeamVariant variant, String[] participants) {

        Map<String, String> teams = new HashMap<>();
        List<String> part = new ArrayList<>(Arrays.stream(participants).toList());

        IntStream.rangeClosed(1, (part.size() / variant.size()))
                 .forEach(i -> IntStream.rangeClosed(1, variant.size())
                                        .forEach(c -> {
                                            if ((teams.get(String.valueOf(i)) == null)) {
                                                teams.put(String.valueOf(i), part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                                            } else {
                                                teams.put(String.valueOf(i), teams.get(String.valueOf(i)) + ";" + part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                                            }
                                        }));
        return teams;
    }

    /**
     * Checks if demanded TeamVariant is possible to the amount of participants.
     * Assigns participants to the teams
     *
     * @param participants
     * @param variant
     * @return Map with TeamVariant as Key and generated Teams as CSV
     */
    public Map<TeamVariant, Map<String, String>> generatePossibleTeam(String participants, TeamVariant variant) {
        Map<TeamVariant, Map<String, String>> assignedTeams = new EnumMap<>(TeamVariant.class);
        if (calculatePossibleTeamVariants(participants.split(";")).contains(variant)) {
            assignedTeams.put(variant, assignParticipantsToTeams(variant, participants.split(";")));
        }
        return assignedTeams;
    }

    /**
     * If array-size modulo TeamVariant.size() == 0, the TeamVariant is possible
     *
     * @param participants
     * @return List of possible TeamVariants
     */
    public List<TeamVariant> calculatePossibleTeamVariants(String[] participants) {
        return Arrays.stream(TeamVariant.values())
                     .filter(v -> participants.length % v.size() == 0 && participants.length != v.size())
                     .toList();
    }
}
