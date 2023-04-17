package primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void testadd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4,5,6);

        assertEquals(new Vector(5,7,9), v1.add(v2),"add vector fail");

        Vector v3 = new Vector(-1, -2 ,-3);
        assertThrows( java.lang.IllegalArgumentException.class,()->{
            v1.add(v3);
            System.out.println("add vector opposite direction fail");
        });
    }

    @Test
    void testscale() {
        Vector v1 = new Vector(1, 2, 3);

        assertEquals(new Vector(2,4,6),v1.scale(2),"scale vector fail");
        assertEquals(new Vector(-2,-4,-6),v1.scale(-2),"scale vector fail - negative scalar");

        assertThrows( java.lang.IllegalArgumentException.class,()->{
            v1.scale(0);
            System.out.println("scale vector by zero fail");
        });

    }

    @Test
    void testdotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4,5,6);

        assertEquals(32 ,v1.dotProduct(v2), "dot product vector fail");

        Vector v3 = new Vector(0, 0, 3);
        Vector v4 = new Vector(4,5,0);

        assertEquals(0 ,v3.dotProduct(v4), "dot product vector fail - should by 0");

    }

    @Test
    void testcrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4,5,6);

        assertEquals(new Vector(-3,6,-3), v1.crossProduct(v2), "cross product vector fail");

        Vector v3 = new Vector(1, 0, 0);
        Vector v4 = new Vector(1,0,0);

        assertThrows( java.lang.IllegalArgumentException.class,()->{
            v3.crossProduct(v4);
            System.out.println("cross product orthonormal vector");
        });

    }

    @Test
    void testlengthSquared() {
        Vector v1 = new Vector(1,2,3);
        assertEquals(14,v1.lengthSquared(),"length Squared vector fail");

    }

    @Test
    void testlength() {
        Vector v1 = new Vector(3,3,3);
        assertEquals(Math.sqrt(27),v1.length(),"length vector fail");
    }

    @Test
    void testnormalize() {
        Vector v1 = new Vector(4,8,3).normalize();
        Vector v2 = new Vector(0, 0, 3).normalize();
        Vector v3 = new Vector(-4,-5,-10).normalize();

        assertEquals(1,v1.length(),"normalize vector fail");
        assertEquals(1,v2.length(),"normalize vector fail");
        assertEquals(1,v3.length(),"normalize vector fail - all cordinate negative");


    }
}