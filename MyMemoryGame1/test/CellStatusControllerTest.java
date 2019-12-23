import controller.CellStatusController;
import model.Cell;
import model.CellStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellStatusControllerTest {

    @Test
    public void swapTest(){
        Cell c = new Cell(5);
        CellStatusController.swap(c);
        Assert.assertEquals(CellStatus.OPENED,
               c.getStatus());
        CellStatusController.swap(c);
        Assert.assertEquals(CellStatus.CLOSSED,
                c.getStatus());
    }

    @Test
    public void setInactiveTest(){
        Cell c = new Cell(5);
        CellStatusController.makeInaktive(c);
        Assert.assertEquals(CellStatus.INACTIVE,
                c.getStatus());
    }

}
