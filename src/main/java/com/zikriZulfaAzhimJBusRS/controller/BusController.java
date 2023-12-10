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

    /**
     *
     * @param accountId
     * @param name
     * @param capacity
     * @param facilities
     * @param busType
     * @param price
     * @param stationDepartureId
     * @param stationArrivalId
     * @return
     */
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
        catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Enum tidak valid", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "Penambahan tidak bisa dieselsaikan", null);
        }
    }

    /**
     *
     * @param busId
     * @param time
     * @return
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ) {
        try {
            // Find the bus by ID
            Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == busId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus tidak ditemukan", null);
            }

            // Parse the timestamp from the provided time string
            Timestamp timestamp = Timestamp.valueOf(time);

            // Add the schedule to the bus
            bus.addSchedule(timestamp);

            // Write the updated data to the JSON file
            busTable.writeJson();

            return new BaseResponse<>(true, "jadwal berhasil ditambahkan", bus);
        } catch (IllegalArgumentException e) {
            // Handle invalid timestamp format or other validation errors
            return new BaseResponse<>(false, "Timestamp tidak valid", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "Penambahan jadwal tidak bisa diselesaikan", null);
        }
    }

    /**
     *
     * @param accountId
     * @return
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);}

    /**
     *
     * @param busId
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse<Bus> deleteBus(@RequestParam int busId) {
        try {
            Bus busToDelete = Algorithm.<Bus>find(busTable, t -> t.id == busId);
            if (busToDelete != null) {
                busTable.remove(busToDelete);
                busTable.writeJson();
                return new BaseResponse<>(true, "Bus berhasil dihapus", null);
            } else {
                return new BaseResponse<>(false, "Bus dengan ID tersebut tidak ditemukan", null);
            }
        } catch (Exception e) {
            return new BaseResponse<>(false, "Terjadi kesalahan dalam menghapus bus", null);
        }
    }

    /**
     *
     * @param busId
     * @param time
     * @return
     */
    @DeleteMapping("/deleteSchedule")
    public BaseResponse<Bus> deleteSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ) {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, t -> t.id == busId);
            if (bus != null) {
                Timestamp timestamp = Timestamp.valueOf(time);
                boolean removed = bus.removeSchedule(timestamp);
                if (removed) {
                    busTable.writeJson();
                    return new BaseResponse<>(true, "Jadwal berhasil dihapus", null);
                } else {
                    return new BaseResponse<>(false, "Jadwal tidak ditemukan pada bus tersebut", null);
                }
            } else {
                return new BaseResponse<>(false, "Bus dengan ID tersebut tidak ditemukan", null);
            }
        } catch (Exception e) {
            return new BaseResponse<>(false, "Terjadi kesalahan dalam menghapus jadwal", null);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/getAllBus")
    public List<Bus> getAllBus() { return getJsonTable();}

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
}
