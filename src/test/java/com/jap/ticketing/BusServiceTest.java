package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class BusServiceTest  {

    BusService busService = null;
    Bus bus;
    String fileName = "target/sample.csv";


    @Before
    public void setUp() {

    busService = new BusService();
    bus = new Bus("KIAS-12/5","KIAS-12UP",9387,1,11359,39,"01/09/2018","02:02:58",281,49.3);
    }

    @After
    public void tearDown() {
    busService = null; bus = null;
    }
    @Test(expected = NumberFormatException.class)
    public void givenWrongDataFormatThrowsNumberFormatException() {
        busService.readFile(fileName);
    }

    @Test
    public void readFileGiven() throws ParseException {
        List<Bus> output = busService.readFile(fileName);
        assertEquals("bus record objects not returned correctly",49,output.size());
    }

    @Test
    public void sortFileByDistance() {
        List<Bus> output = busService.readFile(fileName);
        assertEquals("bus record objects not returned correctly",49,output.size());
    }}

    p