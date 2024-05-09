package ch.swat.choosy.service;

import ch.swat.choosy.bo.TeamVariant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class TeamGenerationService {
    /**
     * Returns a Map with keyvalues of all possible Teamvariations and the corresponding map with keyvalues as the teamnumber and values are the teams as list
     * Map<Possible Team Variant, Map<TeamNr, List of Teammembers>
     *
     * @param participants all participants
     * @return  Map<Possible Team Variant, Map<TeamNr, List of Teammembers>
     */
    public Map<TeamVariant, Map<String, List<String>>> generateAllPossibleTeams(final String participants) {

        Map<TeamVariant, Map<String, List<String>>> possibleTeams = new EnumMap<>(TeamVariant.class);
        calculatePossibleTeamVariants(participants.split(";"))
                .stream()
                .parallel()
                .forEach(teamVariant -> possibleTeams.put(teamVariant, assignParticipantsToTeamsForEachVariant(teamVariant, participants.split(";"))));

        return possibleTeams;
    }

    private Map<String, List<String>> assignParticipantsToTeamsForEachVariant(final TeamVariant variant, final String[] participants) {

        Map<String, List<String>> teams = new HashMap<>();
        List<String> part = new ArrayList<>(Arrays.stream(participants).toList());

        IntStream.rangeClosed(1, (part.size() / variant.size()))
                 .forEach(i -> IntStream.rangeClosed(1, variant.size())
                                        .forEach(j -> {
                                            if ((teams.get(String.valueOf(i)) == null)) {
                                                List<String> list = new ArrayList<>();
                                                list.add(part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                                                teams.put(String.valueOf(i), list);
                                            } else {
                                                teams.get(String.valueOf(i)).add(part.remove(ThreadLocalRandom.current().nextInt(part.size())));
                                            }
                                        }));
        return teams;
    }

    /**
     * Checks if demanded TeamVariant is possible to the amount of participants.
     * Assigns participants to the teams for this TeamVariant
     *
     * @param participants all participants
     * @param teamVariant choosed TeamVariant
     * @return Map with TeamVariant as Key and generated Teams as List
     */
    public Map<TeamVariant, Map<String, List<String>>> generatePossibleTeam(final String participants, final TeamVariant teamVariant) {
        Map<TeamVariant, Map<String, List<String>>> possibleTeams = new EnumMap<>(TeamVariant.class);
        if (calculatePossibleTeamVariants(participants.split(";")).contains(teamVariant)) {
            possibleTeams.put(teamVariant, assignParticipantsToTeamsForEachVariant(teamVariant, participants.split(";")));
        }
        return possibleTeams;
    }

    /**
     * If array-size modulo TeamVariant.size() == 0, the TeamVariant is possible
     *
     * @param participants all participants
     * @return List of possible TeamVariants
     */
    public List<TeamVariant> calculatePossibleTeamVariants(final String[] participants) {
        return Arrays.stream(TeamVariant.values())
                     .filter(teamVariant -> participants.length % teamVariant.size() == 0 && participants.length != teamVariant.size())
                     .toList();
    }
}
