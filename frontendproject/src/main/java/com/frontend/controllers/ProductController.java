package com.frontend.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.daos.CategoryDaos;
import com.backend.daos.ProductDao;
import com.backend.daos.SupplierDao;
import com.backend.models.Category;
import com.backend.models.Product;
import com.backend.models.Supplier;
import com.backend.validators.ProductValidation;

@Controller
public class ProductController {
	
	
	@Autowired
	ProductValidation productValidation;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDaos categoryDaos;
	
	@Autowired
	SupplierDao supplierDao;
    
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public ModelAndView getProductForm()
	{
		
		 
		ModelAndView mv=new ModelAndView("ProductForm");
		Product p=new Product();
		mv.addObject("productObj", p );
		
		List<Category> catList=categoryDaos.getAllCategories();
		List<Supplier> sList=supplierDao.getAllSuppliers();
		
		mv.addObject("categoryList",catList);
		mv.addObject("supplierList",sList);
		mv.addObject("formLabel","Add Product Form");
		mv.addObject("btnLabel","Add Product");
		return mv;
		
	}
	
	@RequestMapping(value="addProductProcess", method=RequestMethod.POST)
	public ModelAndView addProductProcess(@Valid @ModelAttribute("productObj")Product productObj,
	BindingResult result)
	{
		
		productValidation.validate(productObj, result);
		
		if(productObj.getVimage().getSize()==0){
			result.rejectValue("vimage","img.error");
		}
		
		if(result.hasErrors()){
			ModelAndView mv=new ModelAndView("ProductForm");
			
			mv.addObject("categoryList",categoryDaos.getAllCategories());
			mv.addObject("supplierList",supplierDao.getAllSuppliers());
			mv.addObject("btnLabel","Add Product");
			return mv;
		}
		System.out.println("Product Obj = "+productObj);
		ModelAndView mv=new ModelAndView("ViewProducts");
		
		
			
			String filePathString =session.getServletContext().getRealPath("/");
			System.out.println("filePathString : "+filePathString);
			
			
			System.out.println("vimage : "+productObj.getVimage());
			
			
			String fileName=productObj.getVimage().getOriginalFilename();
			
			System.out.println("filname  :"+fileName);
			
			
			
			productObj.setImg(fileName);
			
			System.out.println(productObj+" "+productObj.getProductId());
			if(productObj.getProductId()==null){
				productDao.addProduct(productObj);
			}
			else {
				productDao.updateProduct(productObj);
			}
			
			try{
				
				byte[] imageBytes=productObj.getVimage().getBytes();
				FileOutputStream fos=new FileOutputStream(filePathString+"/resources/images/"+fileName);
				BufferedOutputStream bos= new BufferedOutputStream(fos);
				bos.write(imageBytes);
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mv.addObject("msg","Product Added Successfully");
		
			
		List<Product> pList=productDao.listProducts();
		mv.addObject("products", pList);
		return mv;
		
	}
	
	@RequestMapping(value="viewProducts",method=RequestMethod.GET)
	  public ModelAndView getAllProducts()
	{
		List<Product> pList=productDao.listProducts();
		ModelAndView mv = new ModelAndView("ViewProducts");
		mv.addObject("products",pList);
		return mv;
		
	}
	
	@RequestMapping(value="/deleteProduct/{pId}")
	public ModelAndView deleteProduct(@Valid @PathVariable("pId")int productId)
	{
		Product p=productDao.getProduct(productId);
		productDao.deleteProduct(p);
		
		
		ModelAndView mv=new ModelAndView("ViewProducts");
		
		List<Product> pList=productDao.listProducts();
		mv.addObject("products",pList);
		mv.addObject("msg","Product Deleted Successfully");
		return mv;
	}
	
	
	@RequestMapping(value="editProduct/{pId}")
	 public ModelAndView editProduct(@PathVariable("pId")int productId)
	 {
		
		
		 Product p=productDao.getProduct(productId);
		 ModelAndView mv=new ModelAndView("ProductForm");
		 mv.addObject("productObj",p);
		 mv.addObject("operation","Update");
		 mv.addObject("formLabel","Update Product Form");
		 mv.addObject("btnLabel","Update Product");
		 
		 List<Category> catList=categoryDaos.getAllCategories();
			List<Supplier> sList=supplierDao.getAllSuppliers();
			
			mv.addObject("categoryList",catList);
			mv.addObject("supplierList",sList);
			
		 return mv;
	 }
	
	@RequestMapping(value="getProductsByCategory/{cId}")
	public ModelAndView getProductByCategory(@PathVariable("cId")int categoryId)
	{
		List<Product> pList=productDao.getAllProductsByCategory(categoryId);
		ModelAndView mv=new ModelAndView("ViewProducts");
		mv.addObject("products",pList);
		
		return mv;
	}
	
	
}