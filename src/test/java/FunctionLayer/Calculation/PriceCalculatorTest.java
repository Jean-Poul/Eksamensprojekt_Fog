package FunctionLayer.Calculation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PriceCalculatorTest {

    ArrayList<Item> itemList = new ArrayList();

    @Before
    public void prepareTest(){

        int orderID = 1;
        int sternBoardType = 4;
        int totalSternBoardLength = 510;
        double totalSternBoardPrice = 499.95;
        Item sternBoardItem = new Item(orderID,sternBoardType, totalSternBoardLength, totalSternBoardPrice);
        itemList.add(sternBoardItem);

    }

    @Test
    public void orderLineToDB() {
        //Needs testDB
    }

    @Test
    public void calculateCarportTotalPrice() {
        double expected = itemList.get(0).getTotalPrice() * 2;
        Assert.assertEquals(expected, 999.9, 0.01);
    }

    @Test
    public void costPriceToDB() {
        //Needs testDB
    }

    @Test
    public void itemSearch() {
        //Needs testDB
    }
}