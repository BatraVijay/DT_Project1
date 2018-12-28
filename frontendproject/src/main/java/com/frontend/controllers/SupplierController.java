package com.frontend.controllers;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
        mv.addObject("formLabel","Add Supplier Form");
        mv.addObject("btnLabel","Add Supplier");
        mv.addObject("operation","Add");
        
     
        return mv;
    }
    
    @RequestMapping(value="addSupplierProcess",method=RequestMethod.POST)
    public ModelAndView addSupplierProcess(@Valid@ModelAttribute("supplierObj")Supplier obj,
    	BindingResult result)
    	{
    	 
    	if(result.hasErrors())
    	{
    	ModelAndView mv=new ModelAndView("SupplierForm");
        mv.addObject("btnLabel","Add Supplier");
        mv.addObject("formLabel","Add Supplier Form");
        
		return mv;	
    	}
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
    	 ModelAndView mv=new ModelAndView("SupplierForm");
    	 mv.addObject("supplierObj",supplier);
    	 mv.addObject("formLabel","Update Supplier Form");
         mv.addObject("btnLabel","Update Supplier");
         mv.addObject("operation","Update");
         
    	 
    	 return mv;
    	 
     }
     
}