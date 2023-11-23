package com.zikriZulfaAzhimJBusRS.controller;


import com.zikriZulfaAzhimJBusRS.*;
//import com.zikriZulfaAzhimJBusRS.Payment;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonAutowired;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\src\\main\\java\\com\\zikriZulfaAzhimJBusRS\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;


        @PostMapping("/makeBooking")
        public BaseResponse<Payment> makeBooking(
                @RequestParam int buyerId,
                @RequestParam int renterId,
                @RequestParam int busId,
                @RequestParam List<String> busSeats,
                @RequestParam String departureDate
        ) {

                if (buyerId < 0 || renterId < 0 || busId < 0 || busSeats == null || busSeats.isEmpty() || departureDate == null) {
                    return new BaseResponse<>(false, "Nilai yang diinput salah", null);
                }

                Account buyer = Algorithm.<Account>find(AccountController.accountTable, b -> b.id == buyerId);
                Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == busId);

                if (buyer == null || bus == null) {
                    return new BaseResponse<>(false, "Akun dan Bus tidak ditemukan", null);
                }

                if (buyer.balance < bus.price.price) {
                    return new BaseResponse<>(false, "Saldo tidak cukup", null);
                }

                Schedule schedule = Payment.availableSchedule(Timestamp.valueOf(departureDate), busSeats, bus);
                if (schedule == null) {
                    return new BaseResponse<>(false, "Tidak ada jadwal yang tersedia pada waktu yang diinginkan", null);
                }

                Timestamp timestamp = Timestamp.valueOf(departureDate);
                boolean bookingSuccess = Payment.makeBooking(timestamp, busSeats, bus);

                if (bookingSuccess) {
                    Payment payment = new Payment(buyerId, renterId, busId, busSeats, timestamp);
                    payment.status = Invoice.PaymentStatus.WAITING;

                    paymentTable.add(payment);


                    return new BaseResponse<>(true, "Booking sukses", payment);
                } else {
                    return new BaseResponse<>(false, "Booking gagal", null);
                }

        }

    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> accept(@PathVariable int id) {


            Payment payment = Algorithm.<Payment>find(paymentTable, b -> b.id == id);


            if (payment == null) {
                return new BaseResponse<>(false, "Pembayaran tidak ditemukan", null);
            }


            if (payment.status != Invoice.PaymentStatus.WAITING) {
                return new BaseResponse<>(false, "Pembayaran tidak valid", null);
            }


            payment.status = Invoice.PaymentStatus.SUCCESS;



            return new BaseResponse<>(true, "Pembayaran diterima", payment);

    }

    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancel(@PathVariable int id) {


            Payment payment = Algorithm.<Payment>find(paymentTable, b -> b.id == id);


            if (payment == null) {
                return new BaseResponse<>(false, "Payment tidak ditemukan", null);
            }


            if (payment.status != Invoice.PaymentStatus.WAITING) {
                return new BaseResponse<>(false, "Pembayaran tidak valid", null);
            }


            payment.status = Invoice.PaymentStatus.FAILED;




            return new BaseResponse<>(true, "Pembayaran telah dibatalkan", payment);

    }



    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
}
