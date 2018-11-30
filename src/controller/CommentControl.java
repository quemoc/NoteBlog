package controller;

import java.util.List;



import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CommentService;
import service.UserService;

import bean.Comment;
import bean.User;


@Controller
public class CommentControl {
	@Autowired
	private CommentService commentservice;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/comment")
	public String showAllComment(String user_email,HttpSession session){
		List<Comment> comments=commentservice.findAllComment(user_email);
		session.setAttribute("comments", comments);
		User user = userService.findUserByEmail(user_email);
		session.setAttribute("user", user);
		return "redirect:mycomment.jsp";
	}
	
	@RequestMapping("/delete")
	public String deleteComment(int comment_id,HttpSession session,String user_email){
		commentservice.deleteCommentById(comment_id);
		List<Comment> comments=commentservice.findAllComment(user_email);
		session.setAttribute("comments", comments);
		User user = userService.findUserByEmail(user_email);
		session.setAttribute("user", user);
		return "redirect:mycomment.jsp";
	}
	
}
