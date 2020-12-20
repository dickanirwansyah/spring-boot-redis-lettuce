package id.project.redis.redisletuce.hash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "accounthash")
public class AccountHash implements Serializable {

    @Id
    private String accountId;
    private String roleId;
    private String username;
    private Date lastLogin;
    private String token;
    private String fcmToken;


}
