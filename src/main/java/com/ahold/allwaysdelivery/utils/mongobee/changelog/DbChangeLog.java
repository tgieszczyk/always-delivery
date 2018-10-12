package com.ahold.allwaysdelivery.utils.mongobee.changelog;

import com.ahold.allwaysdelivery.mongobee.changeset.ChangeSet;
import com.ahold.allwaysdelivery.user.repository.UserRepository;
import com.ahold.allwaysdelivery.utils.ApplicationContextProvider;

@com.ahold.allwaysdelivery.mongobee.changeset.ChangeLog(order = "0001")
public class DbChangeLog {
    @ChangeSet(author = "tgieszczyk", id = "0001", order = "0001")
    public void createFewUsers() {
        UserRepository userRepository = ApplicationContextProvider.getApplicationContext().getBean(UserRepository.class);
    }
}
