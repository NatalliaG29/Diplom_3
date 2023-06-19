package User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User getRandomUser(){
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(5));
        user.setEmail(RandomStringUtils.randomAlphabetic(4) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(7));
        return user;
    }
}
