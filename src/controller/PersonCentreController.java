package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.Blog;
import bean.Feedback;
import bean.User;
import service.BlogService;
import service.FeedbackService;
import service.UserService;

@Controller
public class PersonCentreController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping("/list")
	public String showAllBlogs(HttpSession session) {

		System.out.println("拿到了请求------------------------>>>>>>");
		User sessionUser = (User) session.getAttribute("userObj");
		int user_id=sessionUser.getUser_id();
		List<Blog> blogs=blogService.findAllblogByUserId(user_id);
		
		session.setAttribute("blogs", blogs);
		User user = userService.findUserById(user_id);
		session.setAttribute("user", user);
		
		return "redirect:myblog.jsp";
	}

	@RequestMapping("/deletes")
	public String deleteBlog(int blog_id, HttpSession session, int user_id) {
		
		blogService.deleteBlogById(blog_id);
		
		List<Blog> blogs=blogService.findAllblogByUserId(user_id);
		session.setAttribute("blogs", blogs);
		User user = userService.findUserById(user_id);
		session.setAttribute("user", user);

		return "redirect:myblog.jsp";
	}

	@RequestMapping("/insertBack")
	public void insertBack(Feedback feedback, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws ServletException, IOException {
		feedbackService.feedback(feedback);

	}
}
