package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TeamGenerationService {
    public Map<String, String> generateAllPossibleTeams(String participants) {
        calculatePossibleTeamVariants(participants.split(";"));
        return Collections.emptyMap();
    }

    public Map<String, String> generatePossibleTeam(String participants, TeamVariant variant) {
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
