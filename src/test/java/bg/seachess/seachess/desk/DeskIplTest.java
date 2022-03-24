package bg.seachess.seachess.desk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class DeskIplTest {

    private DeskImpl fixture;
    
    @Before
    public void setUp() {
	fixture = new DeskImpl(3);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOutOfRange(){
	fixture.getField(new Position(fixture.getSize()+2, fixture.getSize()+2));
	fail("Should fail if position coordinates are higher than Desk#getSize()");
    }
    
    @Test
    public void testVictoryCondition()
    {
	fixture.occupyPosition(new Position(0,1), 'x');
	fixture.occupyPosition(new Position(1,0), 'o');
	fixture.occupyPosition(new Position(1,1), 'x');
	fixture.occupyPosition(new Position(2,2), '0');
	fixture.occupyPosition(new Position(2,1), 'x');
	
	assertEquals("Expected victory on second column", true, fixture.victoryCondition());
	fixture.resetDesk();
	
	fixture.occupyPosition(new Position(0,0), 'x');
	fixture.occupyPosition(new Position(1,1), 'x');
	fixture.occupyPosition(new Position(2,2), 'x');
	fixture.occupyPosition(new Position(2,1), '0');
	
	assertEquals("Expected victory on main diagonal", true, fixture.victoryCondition());

	fixture.resetDesk();
	
	fixture.occupyPosition(new Position(0,0), 'x');
	fixture.occupyPosition(new Position(0,1), 'x');
	fixture.occupyPosition(new Position(0,2), 'x');
	fixture.occupyPosition(new Position(1,1), '0');
	assertEquals("Expected victory on first column", true, fixture.victoryCondition());
	
	fixture.resetDesk();
	
	assertEquals("Empty Desk, no vicory",  false, fixture.victoryCondition());
	
	fixture.occupyPosition(new Position(0,0), 'x');
	fixture.occupyPosition(new Position(0,1), 'x');
	fixture.occupyPosition(new Position(0,2), 'o');
	fixture.occupyPosition(new Position(1,0), 'o');
	fixture.occupyPosition(new Position(1,1), 'o');
	fixture.occupyPosition(new Position(1,2), 'x');
	fixture.occupyPosition(new Position(2,0), 'x');
	fixture.occupyPosition(new Position(2,1), 'o');
	fixture.occupyPosition(new Position(2,2), 'x');

	assertEquals("Fill Desk with no victory", false, fixture.victoryCondition());
    }
    
    
}
