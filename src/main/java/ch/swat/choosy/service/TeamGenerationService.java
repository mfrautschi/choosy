package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TeamGenerationService {
    public Map<TeamVariant, Map<String, String>> generateAllPossibleTeams(String participants) {
        var variants = calculatePossibleTeamVariants(participants.split(";"));
        Map<TeamVariant, Map<String, String>> assignedTeams = new EnumMap<>(TeamVariant.class);
        for (TeamVariant variant : variants) {
            assignedTeams.put(variant, assignParticipantsToTeams(variant, participants.split(";")));
        }
        return assignedTeams;
    }

    /**
     * Try it with Iterator
     *
     * @param variant
     * @param participants
     * @return
     */
    private Map<String, String> assignParticipantsToTeams(TeamVariant variant, String[] participants) {
        Map<String, String> teams = new HashMap<>();
        List<String> part = new ArrayList<>(Arrays.stream(participants).toList());
        int anzTeams = part.size() / variant.size();

        for (int i = 1; i <= anzTeams; i++) {
            for (int c = 1; c <= variant.size(); c++) {
                if (teams.get(String.valueOf(i))==null) {
                    teams.put(String.valueOf(i), part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                }else{
                    teams.put(String.valueOf(i), teams.get(String.valueOf(i)) + ";" + part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                }
            }
        }
        return teams;
    }

    public Map<TeamVariant, Map<String, String>> generatePossibleTeam(String participants, TeamVariant variant) {
        return Collections.emptyMap();
    }

    public List<TeamVariant> calculatePossibleTeamVariants(String[] participants) {
        List<TeamVariant> possibleTeamVariants = new LinkedList<>();

        for (TeamVariant variant : TeamVariant.values()) {
            if (participants.length % variant.size() == 0 && participants.length != variant.size()) {
                possibleTeamVariants.add(variant);
            }
        }
        return possibleTeamVariants;
    }
}
