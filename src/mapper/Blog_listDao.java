package mapper;

import java.util.List;
import bean.Blog_list;

//������ʾ��ɾ��
public interface Blog_listDao {
	List<Blog_list> findAllblogs();
	void deleteBlogById(int blog_id);
	
}
