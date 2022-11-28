package min.project.fms.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilTest {

    @Test
    void valid_uuid_shoud_return_true(){
        boolean result1 = RegexUtil.isUuid("123e4567-e89b-12d3-a456-426614174000");
        assertTrue(result1);
    }

    @Test
    void invalid_uuid_shoud_return_false(){
        boolean result2 = RegexUtil.isUuid("123e4567e89b12d3a456426614174000");
        assertFalse(result2);
    }

}