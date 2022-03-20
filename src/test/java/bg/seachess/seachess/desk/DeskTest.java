package bg.seachess.seachess.desk;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class DeskTest {

    private Desk fixture;
    
    @Before
    public void setUp() {
	fixture = new Desk(3);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOutOfRange(){
	fixture.getField(new Position(fixture.getSize()+2, fixture.getSize()+2));
	fail("Should fail if position coordinates are higher than Desk#getSize()");
	
    }
}
