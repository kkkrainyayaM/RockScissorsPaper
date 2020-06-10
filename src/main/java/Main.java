import game.Startable;
import game.impl.Game;
import validators.Validator;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> entities = Arrays.asList(args);

        if (Validator.isNotValidEntities(entities)) {
            System.out.println("Wrong arguments!");
            return;
        }

        Startable game = new Game(entities);
        game.start();
    }
}

