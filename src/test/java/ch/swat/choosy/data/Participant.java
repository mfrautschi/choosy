package ch.swat.choosy.data;

public enum Participant {
    participants2("Bruno;Josef"),
    participants3("Bruno;Josef;Alina"),
     participants4("Bruno;Josef;Alina;Michelle"),
     participants5("Bruno;Josef;Alina;Michelle;Tom"),
     participants6("Bruno;Josef;Alina;Michelle;Tom;Luca"),
     participants7("Bruno;Josef;Alina;Michelle;Tom;Luca;Elena"),
     participants8("Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina"),
     participants9("Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard"),
     participants10("Bruno;Josef;Alina;Michelle;Tom;Luca;Elena;Nina;Bernhard;Marco");

    private final String names;

    Participant(String name) {
        this.names = name;
    }

    public String names() {
        return names;
    }
}
