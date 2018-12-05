package com.frontend.controllers;
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 



import com.backend.daos.SupplierDao;
import com.backend.models.Supplier;
 
@Controller
public class SupplierController {
 
	@Autowired
    SupplierDao supplierDao;
    
    @RequestMapping(value="addSupplier",method=RequestMethod.GET)
    public ModelAndView getSupplierForm()
    {
        ModelAndView mv=new ModelAndView("SupplierForm");
        Supplier cat=new Supplier();
        mv.addObject("supplierObj",cat);
        return mv;
    }
    
    @RequestMapping(value="addSupplierProcess",method=RequestMethod.POST)
    public ModelAndView addSupplierProcess(@ModelAttribute("supplierObj")Supplier obj){
         
        ModelAndView mv=new ModelAndView("ViewSupplier");
        if(obj.getSupplierId()==0){
            mv.addObject("msg","Supplier Added Succesfully...");
            supplierDao.addSupplier(obj);
        }
        else {
        	
        	 mv.addObject("msg","Supplier Updated Succesfully...");
             supplierDao.updateSupplier(obj);
         }
          
         List<Supplier> catList=supplierDao.getAllSuppliers();
         mv.addObject("suppliers",catList);
         return mv;
     }
      
     @RequestMapping(value="viewAllSuppliers",method=RequestMethod.GET)
     public ModelAndView getAllSupplier(){
          
         List<Supplier> suppList=supplierDao.getAllSuppliers();
          
         ModelAndView mv=new ModelAndView("ViewSupplier");
         mv.addObject("suppliers",suppList);
         return mv;
     }
     @RequestMapping(value="deleteSupplier/{catId}")
     public ModelAndView deleteSupplier(@PathVariable("catId")int supplierId){
          
    	 Supplier cat=supplierDao.getSupplierById(supplierId);
    	 supplierDao.deleteSupplier(cat);
          
         List<Supplier> supplierList=supplierDao.getAllSuppliers();
         ModelAndView mv=new ModelAndView("ViewSupplier");
         mv.addObject("suppliers",supplierList);
         mv.addObject("msg","Supplier Deleted Succesfully");
         return mv;
     }
     
     @RequestMapping(value="updateSupplier/{catId}")
     public ModelAndView updateSupplier(@PathVariable("catId")int supplierId){
    	 
    	 Supplier supplier=supplierDao.getSupplierById(supplierId);
    	 supplierDao.updateSupplier(supplier);
    	 
    	 List<Supplier> supplierList=supplierDao.getAllSuppliers();
    	 ModelAndView mv=new ModelAndView("SupplierForm");
    	 mv.addObject("supplierObj",supplier);
    	 mv.addObject("msg","Supplier Updated Sucessfully");
    	 return mv;
    	 
     }
     
}