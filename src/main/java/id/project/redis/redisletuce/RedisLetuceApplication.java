package id.project.redis.redisletuce;

import id.project.redis.redisletuce.hash.AccountHash;
import id.project.redis.redisletuce.repository.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class RedisLetuceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisLetuceApplication.class, args);
	}

}


@Component
class CommandData implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	@Override
	public void run(String... args) throws Exception {
		AccountHash accountHash = new AccountHash();
		accountHash.setAccountId("USR-"+ UUID.randomUUID().toString().substring(0, 5));
		accountHash.setFcmToken(UUID.randomUUID().toString());
		accountHash.setLastLogin(new Date());
		accountHash.setRoleId("teller");
		accountHash.setToken(UUID.randomUUID().toString());
		accountHash.setUsername("budi");
		accountService.createSession(accountHash);

		/** for list data **/
		Map<String, Object> dataAllredis = accountService.getAll();
		System.out.println("### all data redis cache ###");
		System.out.println(dataAllredis);
		System.out.println("### end data redis caache ###");
	}
}

