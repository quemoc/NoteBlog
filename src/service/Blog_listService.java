package service;

import java.util.List;

import bean.Blog_list;
//��������ģ����ʾ���˲��ͼ�ɾ��
public interface Blog_listService {
	List<Blog_list> findAllblogs();
	void deleteBlogById(int blog_id);
}
