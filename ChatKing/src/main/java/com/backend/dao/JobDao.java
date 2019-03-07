package com.backend.dao;

import java.util.List;

import com.backend.model.ApplyJob;
import com.backend.model.Job;

public interface JobDao {

	
	public boolean addJob(Job job);//insert into job table
	public List<Job> getAllJobs();//select * from job table
	public Job getJob(int id);
	boolean applyJob(ApplyJob applyJob);
	public List<ApplyJob> getAllAppliedJobs(String email);
	ApplyJob checkIfApplied(String email,int jobId); 

}
