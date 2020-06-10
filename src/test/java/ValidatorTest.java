import org.junit.Assert;
import org.junit.Test;
import validators.Validator;

import java.util.Arrays;
import java.util.Collections;

public class ValidatorTest {

    @Test
    public void isValidEntitiesTest() {
        Assert.assertTrue(Validator.isNotValidEntities(Collections.EMPTY_LIST));
        Assert.assertTrue(Validator.isNotValidEntities(Collections.singletonList("entity1")));
        Assert.assertTrue(Validator.isNotValidEntities(Arrays.asList("entity1", "entity2", "entity3", "entity4")));
        Assert.assertFalse(Validator.isNotValidEntities(Arrays.asList("entity1", "entity2", "entity3", "entity4", "entity5")));
    }
}
