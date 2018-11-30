package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Comment;
import mapper.CommentDao;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentdao;

	public List<Comment> findAllCommentByBlogId(int blog_id) {
		
		return commentdao.findAllCommentByBlogId(blog_id);
	}

	
	public int insertComment(Comment comment) {

		return commentdao.insertComment(comment);
	}


	//新增
	@Override
	public List<Comment> findAllComment(String user_email) {
		
		 return commentdao.findAllComment(user_email);
	}


	@Override
	public void deleteCommentById(int comment_id) {
		
		commentdao.deleteCommentById(comment_id);
	}

}
