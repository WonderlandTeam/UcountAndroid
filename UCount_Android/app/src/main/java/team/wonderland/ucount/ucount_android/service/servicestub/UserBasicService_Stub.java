package team.wonderland.ucount.ucount_android.service.servicestub;

import team.wonderland.ucount.ucount_android.json.UserSignUpJson;
import team.wonderland.ucount.ucount_android.service.UserBasicService;

/**
 * Created by CLL on 17/8/23.
 */
public class UserBasicService_Stub implements UserBasicService {
    @Override
    public Long signUp(UserSignUpJson userSignUpJson) {
        return Long.valueOf(1);
    }
}
