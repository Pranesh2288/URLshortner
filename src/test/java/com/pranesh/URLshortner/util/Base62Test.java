package com.pranesh.URLshortner.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62Test {

    @Test
    public void testEncoding() {
        // Test that ID 125 converts to something specific
        // 125 / 62 = 2 (remainder 1) -> maps to characters
        String result = Base62.encode(125);
        
        // Let's verify our math. If 0 is 'a', 1 is 'b'...
        // You can run this and see what it outputs!
        System.out.println("Encoded 125 is: " + result);
    }

    @Test
    public void testZeroCase() {
        assertEquals("a", Base62.encode(0));
    }
}