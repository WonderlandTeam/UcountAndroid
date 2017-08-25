package team.wonderland.ucount.ucount_android.service.servicestub;

import team.wonderland.ucount.ucount_android.json.UserSignUpJson;
import team.wonderland.ucount.ucount_android.service.UserBasicService;

import java.util.Map;

/**
 * Created by CLL on 17/8/23.
 */
public class UserBasicService_Stub implements UserBasicService {
    @Override
    public Map<String,Object> signUp(UserSignUpJson userSignUpJson) {
        return null;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        return null;
    }
}
