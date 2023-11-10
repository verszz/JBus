package com.zikriZulfaAzhimJBusRS.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.zikriZulfaAzhimJBusRS.Account;
import com.zikriZulfaAzhimJBusRS.Renter;
import com.zikriZulfaAzhimJBusRS.Algorithm;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonAutowired;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{

    @JsonAutowired (value = Account.class, filepath = "C:\\Users\\ASUS\\Documents\\Tugas Zikri\\Semester 3\\OOP\\Praktikum\\JBus\\src\\main\\java\\com\\zikriZulfaAzhimJBusRS\\json\\account.json")
    public static JsonTable<Account> accountTable;
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    BaseResponse<Account>Register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            ) {
        Boolean CheckBlank = name.isBlank();
        Boolean CheckDuplicate = Algorithm.<Account>exists(accountTable, a -> a.email.equals(email));
        Boolean CheckValidityRegex = new Account(name, email, password).validate();

        if (CheckDuplicate) {
            return new BaseResponse<>(false, "Email tidak ada", null);
        }
        if (CheckBlank) {
            return new BaseResponse<>(false, "Mohon mengisi kolom nama", null);
        }
        if (!CheckValidityRegex) {
            return new BaseResponse<>(false, "Email atau password tidak mengikuti format", null);
        }

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update(password.getBytes());

            byte[] bytes = md5.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            password = sb.toString();
        } catch (NoSuchAlgorithmException a) {
            a.printStackTrace();
        }
        Account makeAcc = new Account(name, email, password);
        accountTable.add(makeAcc);
        return new BaseResponse<>(true, "Akun teregistrasi", makeAcc);
    }

    @PostMapping("/login")
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password
    ) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            password = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        Account logAccount = Algorithm.<Account>find(accountTable, acc -> acc.email.equals(email));

        if (logAccount == null) {
            return new BaseResponse<>(false, "Email tidak terdaftar", null);
        }

        if (!logAccount.password.equals(password)){
            return new BaseResponse<>(false, "Password yang dimasukkan salah", null);
        }

        return new BaseResponse<>(true, "Login berhasil", logAccount);
    }


    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter (
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        Account renterAccount = Algorithm.<Account>find(getJsonTable(), acc -> acc.id == id);
        if(renterAccount == null) {
            return new BaseResponse<>(false, "Akun dengan id tersebut tidak ada", null);
        }

        if(renterAccount.company != null) {
            return new BaseResponse<>(false, "Akun tersebut sudah terdaftar sebagai renter", null);
        }

        Renter newRenter = new Renter(companyName, phoneNumber, address);
        renterAccount.company = newRenter;

        return new BaseResponse<>(true, "Registrasi renter berhasil", newRenter);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double amount
    ) {
        Account topUpAccount = Algorithm.<Account>find(getJsonTable(), acc -> acc.id == id);
        if(topUpAccount == null) {
            return new BaseResponse<>(false, "Akun dengan id tersebut tidak ada", null);
        }

        if(!topUpAccount.topUp(amount)){
            return new BaseResponse<>(false, "Top up harus lebih dari 0", 0.0);
        }

        return new BaseResponse<>(true, "Top up Berhasil", amount);
    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return null;
    }
}