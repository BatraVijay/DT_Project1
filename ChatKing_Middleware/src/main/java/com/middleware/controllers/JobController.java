package com.middleware.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dao.JobDao;
import com.backend.dao.UserDao;
import com.backend.model.ApplyJob;
import com.backend.model.MyError;
import com.backend.model.Job;
import com.backend.model.User;

@Controller
@RestController
public class JobController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobDao jobDao;
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody Job job,HttpSession session){
		
		//Authentication
				String email=(String) session.getAttribute("loginId");
				System.out.println("Email in Job Controller : "+email);
				
				
				User user=userDao.getUser(email);

		try
		{
			job.setPostedOn(new Date());
			jobDao.addJob(job);
			return new ResponseEntity <Job>(job,HttpStatus.OK);
		}
		catch(Exception e)
		{
			MyError error=new MyError("unable to add job post.."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value="/alljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		 List<Job> listJobs=jobDao.getAllJobs();
		 System.out.println("All Jobs in controller");
		   if(listJobs.size()!=0)
		   {
			   
			   return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);
		   }
		   else
		   {
				MyError error=new MyError("Job not Found");

			   return new ResponseEntity<MyError>(error,HttpStatus.NOT_FOUND);
		   }
	}

	@RequestMapping(value="/applyjob",method=RequestMethod.POST)
	public ResponseEntity<?> applyJob(@RequestBody int jobId,HttpSession session){
		
		//Authentication
		User userobj=(User)session.getAttribute("userObj");
		System.out.println("Email in Job Controller : "+userobj);
		
		if(userobj==null){
			MyError error=new MyError("Unauthorized User");
			return new ResponseEntity <MyError>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String email=userobj.getEmail();
		User user=userDao.getUser(email);
		System.out.println("user in Job Controller:"+user);
		
		try
		{
			ApplyJob job=new ApplyJob();
			job.setJobId(jobId);
			
			
			job.setEmail(email);
			job.setAppliedOn(new Date());
			jobDao.applyJob(job);
			return new ResponseEntity <ApplyJob>(job,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MyError error=new MyError("unable to apply job .."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getjob/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int id,HttpSession session){
		Job job=jobDao.getJob(id);
		System.out.println("job = "+job+" and id = "+id);
		return new ResponseEntity <Job>(job,HttpStatus.OK);
	}
	@RequestMapping(value="/allAppliedJobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllAppliedJobs(HttpSession session)
	{
		List<ApplyJob> jobs=jobDao.getAllAppliedJobs(null);
		if(jobs.size()==0){
			System.out.println("No jobs applied");
			MyError error=new MyError("No Jobs Applied");
			return new ResponseEntity<MyError>(error,HttpStatus.NOT_FOUND);
		}	
		else{
		return new ResponseEntity <List<ApplyJob>>(jobs,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/checkAppliedJobs/{jobId}",method=RequestMethod.GET)
	public ResponseEntity<?> checkAppliedJobs(@PathVariable int jobId,HttpSession session)
	{
	
		User userobj=(User) session.getAttribute("userObj");
		if(userobj==null){
			MyError error=new MyError("Cant check applied jobs if user has not logged in");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);
		}
		String email=userobj.getEmail();
		
		ApplyJob applyJob=jobDao.checkIfApplied(email, jobId);
		if(applyJob==null){
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}	
		else{
		return new ResponseEntity <Boolean>(true,HttpStatus.OK);
		}
	}

}
