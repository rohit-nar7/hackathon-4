package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BusService {

    public List<Bus> readFile(String fileName) {
        // read the file line by line
        List<Bus> records = new ArrayList<>();
        int countLines = 0;
        Bus[] bus = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                countLines++;
            }
            bus = new Bus[countLines];

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                int index = 0;
                String[] split = line.split(",");
                String schedule_no = split[0];
                String route_no = split[1];
                int ticket_from_stop_id = Integer.parseInt(split[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(split[3]);
                int ticket_till_stop_id = Integer.parseInt((split[4]));
                int ticket_till_stop_seq_no = Integer.parseInt((split[5]));
                String ticket_date = split[6];
                String ticket_time = split[7];
                int total_ticket_amount = Integer.parseInt(split[8]);
                double travelled_KM = Double.parseDouble(split[9]);

                records.add(new Bus(schedule_no, route_no, ticket_from_stop_id, ticket_from_stop_seq_no,
                        ticket_till_stop_id, ticket_till_stop_seq_no, ticket_date, ticket_time, total_ticket_amount, travelled_KM));
            }
        } catch (FileNotFoundException e) {
            throw new NumberFormatException("bus record objects not returned correctly");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public List<Bus> getDistanceTravelledByBusSorted(List<Bus> records) {
        System.out.println("\n--------------------------------------------------------------------------------\n");
        Comparator<Bus> comparator = (s1, s2) -> (int) (s1.getTravelledKM() - s2.getTravelledKM());
        //sort by travelled_KM
        Collections.sort(records , comparator.reversed());
        for(Bus element : records) {
            System.out.println(element);
        }
        return records;
    }

    public int getTotalCollectionOfTickets(List<Bus> records) {
        //get total_ticket_amount

        int total = 0;
        for (int i = 0; i < records.size() ; i++) {
            total = total + records.get(i).getTotalTicketAmount();
        }
        return total;
    }

    public static void main(String[] args) {
        BusService busService = new BusService();
        List<Bus> records =   busService.readFile("sample.csv");
        System.out.println(busService.getDistanceTravelledByBusSorted(records));
        System.out.println(busService.getTotalCollectionOfTickets(records));


    }
}
