package com.backend.dao;

import java.util.List;

import com.backend.model.MyNotification;

public interface MyNotificationDao {

	MyNotification addNotification(MyNotification obj);
	MyNotification getNotificationById(int id);
	List<MyNotification> getAllNotifications(String userEmail);
	MyNotification update(MyNotification obj);
	

}


