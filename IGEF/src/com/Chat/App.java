package com.Chat;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.Prefrence.IGEFSharedPrefrence;
import com.SocialNetwork.igef.IgefSocailNetwork;
import com.parse.Parse;
import com.parse.PushService;
import com.quickblox.module.chat.QBChatRoom;
import com.quickblox.module.users.model.QBUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App extends Application {

    private QBUser qbUser;
    private int currentPage = 1;
    private Map<Integer, QBUser> allQbUsers = new HashMap<Integer, QBUser>();
    private Map<Integer, List<ChatMessage>> allMessages = new HashMap<Integer, List<ChatMessage>>();
    private QBChatRoom currentRoom;
    private static App instance;

    public static App getInstance() {
        return instance;
    }
    public int getAppVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    @Override
    public void onCreate() {
    	IGEFSharedPrefrence obj=new IGEFSharedPrefrence(getApplicationContext());
    	 Parse.initialize(this, "mi3qCGDTbjdgmWGIehsgBSo0uEZrlPO9mgF1vCF2", "huZuXAtIPUMVfKrQ4fhJsfZJbe8PvU8iB3StObVG");
    	 PushService.setDefaultPushCallback(this, IgefSocailNetwork.class);
        super.onCreate();
    }

    public QBUser getQbUser() {
        return qbUser;
    }

    public void setQbUser(QBUser qbUser) {
        this.qbUser = qbUser;
    }

    public List<QBUser> getAllQbUsers() {
        List<QBUser> qbUsers = new ArrayList<QBUser>(allQbUsers.values());
        Collections.sort(qbUsers, new Comparator<QBUser>() {
            @Override
            public int compare(QBUser lhs, QBUser rhs) {
                return (int) Math.signum(lhs.getId() - rhs.getId());
            }
        });
        return qbUsers;
    }

    public void addQBUsers(QBUser... qbUsers) {
        for (QBUser qbUser : qbUsers) {
            allQbUsers.put(qbUser.getId(), qbUser);
        }
    }

    public List<ChatMessage> getMessages(int userId) {
        return allMessages.get(userId);
    }

    public void addMessage(int userId, ChatMessage message) {
        List<ChatMessage> messages = allMessages.get(userId);
        if (messages == null) {
            messages = new ArrayList<ChatMessage>();
            allMessages.put(userId, messages);
        }
        messages.add(message);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public QBChatRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(QBChatRoom room) {
        this.currentRoom = room;
    }
}
