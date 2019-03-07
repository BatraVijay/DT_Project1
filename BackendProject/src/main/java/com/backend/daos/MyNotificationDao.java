package com.backend.daos;

import java.util.List;

import com.backend.models.MyNotification;

public interface MyNotificationDao {

	MyNotification addNotification(MyNotification obj);
	MyNotification getNotificationById(int id);
	List<MyNotification> getAllNotifications(String userEmail);
	MyNotification update(MyNotification obj);
	

}


