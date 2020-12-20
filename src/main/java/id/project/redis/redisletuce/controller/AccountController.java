package id.project.redis.redisletuce.controller;

import id.project.redis.redisletuce.hash.AccountHash;
import id.project.redis.redisletuce.model.Account;
import id.project.redis.redisletuce.repository.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/session")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> all(){
        Map<String, Object> allSessions = accountService.getAll();
        return new ResponseEntity<>(allSessions, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody Account account){

        String success = "";
        try{
            AccountHash accHash = AccountHash.builder()
                    .accountId("USR-"+ UUID.randomUUID().toString().substring(0, 5))
                    .fcmToken(UUID.randomUUID().toString())
                    .lastLogin(new Date())
                    .roleId("teller")
                    .token(UUID.randomUUID().toString())
                    .username(account.getUsername())
                    .build();
            accountService.createSession(accHash);
            success = "success";
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam("userId")String userId){
        String success = "";
        try{
            accountService.delete(userId);
            success = "success";
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
