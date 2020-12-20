package id.project.redis.redisletuce.repository;

import id.project.redis.redisletuce.hash.AccountHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Repository
public class AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountService.class);
    private HashOperations hashOperations;
    /** token expired satuan menit **/
    private static int tokenExpired = 10;
    private static String key = "ACC";

    public AccountService(RedisTemplate redisTemplate){
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void createSession(AccountHash accountHash){
        hashOperations.put(key, accountHash.getAccountId(), accountHash);
    }

    public AccountHash get(String accountId){
        return (AccountHash) hashOperations.get(key, accountId);
    }

    public Map<String, Object> getAll(){
        return hashOperations.entries(key);
    }

    public void update(AccountHash accountHash){
        hashOperations.put(key, accountHash.getAccountId(), accountHash);
    }

    public void delete(String accountId){
        hashOperations.delete(key, accountId);
    }


}
