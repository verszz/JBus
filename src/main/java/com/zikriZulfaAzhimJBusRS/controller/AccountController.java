package com.zikriZulfaAzhimJBusRS.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.zikriZulfaAzhimJBusRS.Account;
import com.zikriZulfaAzhimJBusRS.Renter;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonAutowired;
import com.zikriZulfaAzhimJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{

    public static @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\zikriZulfaAzhimJBusRS\\json\\account.json") JsonTable<Account> accountTable;
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        Account acc = new Account(name, email, password);
        String hexadecimalPassword = null;
        if(name.isBlank()){
            return new BaseResponse<>(false, "Gagal Registrasi", null);
        }

        if(!acc.validate()){
            return new BaseResponse<>(false, "Gagal Registrasi", null);
        }
        if (accountTable.exists(account -> acc.getEmail().equals(email))) {
            return new BaseResponse<>(false, "Gagal register", null);
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(acc.password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x00, 16).substring(1));
            }
            hexadecimalPassword = sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        Account newAccount = new Account(name, email, hexadecimalPassword);
        accountTable.add(newAccount);

        return new BaseResponse<>(true, "Berhasil register", newAccount);
    }


    @PostMapping("/login")
    BaseResponse<Account> login
            (
                    @RequestParam String email,
                    @RequestParam String password
            )
    {

    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter
            (
                    @PathVariable int id,
                    @RequestParam String companyName,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            )
    {

    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp
            (
                @PathVariable int id,
                @RequestParam double amount
            )
    {

    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return null;
    }
}
