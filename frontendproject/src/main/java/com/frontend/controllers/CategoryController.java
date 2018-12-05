package com.frontend.controllers;
 
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 

import com.backend.daos.CategoryDaos;
import com.backend.models.Category;
 
@Controller
public class CategoryController {
 
    @Autowired
    CategoryDaos categoryDaos;
     
    @RequestMapping(value="addCategory",method=RequestMethod.GET)
    public ModelAndView getCategoryForm(){
        ModelAndView mv=new ModelAndView("CategoryForm");
        Category cat=new Category();
        mv.addObject("categoryObj",cat);
        return mv;
    }
     
    @RequestMapping(value="addCategoryProcess",method=RequestMethod.POST)
    public ModelAndView addCategoryProcess(@ModelAttribute("categoryObj")Category obj){
         
        ModelAndView mv=new ModelAndView("ViewCategories");
        if(obj.getCategoryId()==0){
            mv.addObject("msg","Category Added Succesfully...");
            categoryDaos.addCategory(obj);
        }
        else {
            mv.addObject("msg","Category Updated Succesfully...");
            categoryDaos.updateCategory(obj);
        }
         
        List<Category> catList=categoryDaos.getAllCategories();
        mv.addObject("categories",catList);
        return mv;
    }
     
    @RequestMapping(value="viewAllCategories",method=RequestMethod.GET)
    public ModelAndView getAllCategories(){
         
        List<Category> catList=categoryDaos.getAllCategories();
         
        ModelAndView mv=new ModelAndView("ViewCategories");
        mv.addObject("categories",catList);
        return mv;
    }
     
     
    @RequestMapping(value="deleteCategory/{catId}")
    public ModelAndView deleteCategory(@PathVariable("catId")int categoryId){
         
        Category cat=categoryDaos.getCategoryById(categoryId);
        categoryDaos.deleteCategory(cat);
         
        List<Category> catList=categoryDaos.getAllCategories();
        ModelAndView mv=new ModelAndView("ViewCategories");
        mv.addObject("categories",catList);
        mv.addObject("msg","Category Deleted Succesfully");
        return mv;
    }
     
    @RequestMapping(value="editCategory/{catId}")
    public ModelAndView editCategory(@PathVariable("catId")int categoryId){
         
        Category category=categoryDaos.getCategoryById(categoryId);
         
        ModelAndView mv=new ModelAndView("CategoryForm");
        mv.addObject("categoryObj",category);
        mv.addObject("formLabel","Update Category Form");
        mv.addObject("btnLabel","Update Category");
        mv.addObject("operation","Update");
        return mv;
    }
}