package com.zikriZulfaAzhimJBusRS.controller;


import com.zikriZulfaAzhimJBusRS.*;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonAutowired;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import com.zikriZulfaAzhimJBusRS.Renter;
import com.zikriZulfaAzhimJBusRS.Account;

import static com.zikriZulfaAzhimJBusRS.controller.AccountController.accountTable;
import static com.zikriZulfaAzhimJBusRS.controller.StationController.stationTable;


@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>
{
    @JsonAutowired(value = Bus.class, filepath = "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\src\\main\\java\\com\\zikriZulfaAzhimJBusRS\\json\\bus.json")
    public static JsonTable<Bus> busTable;


    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){
        try{
            Account account = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == accountId);
        if (account == null || account.company == null) {
            return new BaseResponse<>(false, "Akun tidak ada", null);
        }
        Station stationDep = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationDepartureId);
        Station stationArr = Algorithm.<Station>find(StationController.stationTable, stat -> stat.id == stationArrivalId);
        if(stationDep == null || stationArr == null){
            return new BaseResponse<>(false, "Kota keberangkatan/kedatangan tidak ada ", null);
        }

        Bus newbus = new Bus(
                accountId,
                name,
                facilities,
                new Price(price),
                capacity,
                busType,
                stationDep,
                stationArr
        );
        busTable.add(newbus);
        busTable.writeJson();
        return new BaseResponse<>(true, "Bus baru telah didaftarkan", newbus);}
        catch (IllegalArgumentException | IOException e){
            return new BaseResponse<>(false, "Bus gagal dibuat",null);
        }
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int BusId,
            @RequestParam String time
    ){
        Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == BusId);
        if(bus == null){
            return new BaseResponse<>(false, "Bis tidak ditemukan",null);
        }
        bus.addSchedule(Timestamp.valueOf(time));
        return new BaseResponse<>(true, "Jadwal telah ditambahkan", bus);

    }

    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);}

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
}
