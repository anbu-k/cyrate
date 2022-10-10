package com.example.cyrate.Logic;

import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.models.UserModel;

import java.util.List;

public interface getAllUsersResponse {
    public void onSuccess(List<UserModel> list);
    public void onError(String s);

}
