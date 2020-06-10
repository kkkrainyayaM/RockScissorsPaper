package validators;

import java.util.HashSet;
import java.util.List;

public final class Validator {

    private static final int MIN_FIGURES = 3;

    private Validator() {
    }

    public static boolean isNotValidEntities(List entities){
        return entities.size() % 2 == 0
                || entities.size() < MIN_FIGURES
                || !containsUniqueValues(entities);
    }

    private static boolean containsUniqueValues(List figures) {
        return new HashSet<Object>(figures).size() == figures.size();
    }
}
