package com.backend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.dao.JobDao;
import com.backend.model.ApplyJob;
import com.backend.model.Job;

@Repository()
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public boolean addJob(Job job)
	{
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.save(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<Job> getAllJobs() {
		try
		{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		List<Job> listJobs= query.list();
		System.out.println("listJobs in dao impl: "+listJobs);
		return listJobs;
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean applyJob(ApplyJob applyJob) {
		Session session=sessionfactory.getCurrentSession();
		session.save(applyJob);
		return true;
	}

	@Override
	public List<ApplyJob> getAllAppliedJobs(String email) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from ApplyJob where email=:x");
		query.setParameter("x", email);
		return query.list();
	}
	@Override
	public Job getJob(int id) {
		try
		{
		Session session=sessionfactory.getCurrentSession();
		Job job=session.get(Job.class,id);
		return job;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	@Override
	public ApplyJob checkIfApplied(String email, int jobId) {
		Session session=sessionfactory.getCurrentSession();
		System.out.println("job Id   : "+jobId);
		System.out.println("email : "+email);
		Query query=session.createQuery("from ApplyJob where jobId=:x and email=:y");
		query.setParameter("x",jobId);
		query.setParameter("y",email);
		
		List<ApplyJob> appliedJobs=query.list();
		if(appliedJobs.size()==0){
			return null;
		}
		else {
		return appliedJobs.get(0);
		}
	}

}
