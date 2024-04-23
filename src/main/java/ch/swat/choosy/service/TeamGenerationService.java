package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
        int anzTeams = part.size() / variant.size();
        for (int i = 1; i <= anzTeams; i++) {
            for (int c = 1; c <= variant.size(); c++) {
                if (teams.get(String.valueOf(i)) == null) {
                    teams.put(String.valueOf(i), part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                } else {
                    teams.put(String.valueOf(i), teams.get(String.valueOf(i)) + ";" + part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                }
            }
        }
        return teams;
    }

    public Map<TeamVariant, Map<String, String>> generatePossibleTeam(String participants, TeamVariant variant) {
        Map<TeamVariant, Map<String, String>> assignedTeams = new EnumMap<>(TeamVariant.class);
        if (calculatePossibleTeamVariants(participants.split(";")).contains(variant)) {
            assignedTeams.put(variant, assignParticipantsToTeams(variant, participants.split(";")));
        }
        return assignedTeams;
    }

    public List<TeamVariant> calculatePossibleTeamVariants(String[] participants) {
        return Arrays
                .stream(TeamVariant.values())
                .filter(v -> participants.length % v.size() == 0 && participants.length != v.size())
                .toList();
    }
}
