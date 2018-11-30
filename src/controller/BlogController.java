package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Blog;
import bean.Blogs;
import bean.User;
import service.BlogService;
import service.UserService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/addBlog")
	@ResponseBody
	public void addBlog(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try {
		User userObj = (User)session.getAttribute("userObj");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		Blog blog=new Blog();
		String title=request.getParameter("title");
		String article_class=request.getParameter("class");
		String desc=request.getParameter("desc");
		String tags=request.getParameter("tags");
		String context=request.getParameter("editor");
		blog.setBlog_content(context);
		blog.setBlog_class(article_class);
		blog.setBlog_summary(desc);
		blog.setBlog_title(title);
		blog.setBlog_tag(tags);
		blog.setUser_id(userObj.getUser_id());

		System.out.println(blog);
		int savablog = blogService.saveBlog(blog);
		if(savablog==1){
			//插入成功
			out.println(1);
		}else{
			out.println(0);
		}
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	@RequestMapping("/article_center")
	public String article_center(HttpSession session){
		User u = (User)session.getAttribute("userObj");
		User result = userService.findUserById(u.getUser_id());
		String userName = result.getUser_name();
		session.setAttribute("user_name", userName);
		session.setAttribute("user_email", result.getUser_email());
		return "redirect:article_center.jsp";
		
	}
	
	
	@RequestMapping("/admin/deleteBlog")
	public void delBlogById(int blog_id){
		/*调用删除方法*/
		int result = blogService.deleteBlogById(blog_id);
		if(result==1){
			System.out.println("删除成功!!");
		}else{
			System.out.println("删除失败!!");
		}
	}
	
	@RequestMapping("/admin/allBlog")
	@ResponseBody
	public List<Blog> findAllBlog(){
		/*通过接口获取所有blog条目*/
		List<Blog> AllBlog = blogService.findAllBlog();
		return AllBlog;
	}
	
	
	
	@RequestMapping("/admin/allBlogs")
	@ResponseBody
	public List<Blogs> findAllBlogs(){
		/*通过接口获取所有blog条目*/
		List<Blogs> blogs=new ArrayList<Blogs>();
		 List<Blog> Blog = blogService.findAllBlog();
		 for (int i = 0; i < Blog.size(); i++) {
			Blogs bls=new Blogs();
			Blog blog = Blog.get(i);
			int user_id = blog.getUser_id();
			User findUserById = userService.findUserById(user_id);
			String userName = findUserById.getUser_name();
			bls.setBlog(blog);
			bls.setUser_name(userName);
			blogs.add(bls);
		}
		return blogs;
	}
}
