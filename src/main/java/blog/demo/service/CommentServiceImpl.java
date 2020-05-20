package blog.demo.service;

import blog.demo.dao.CommentRepository;
import blog.demo.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        return commentRepository.findByBlogId(blogId, Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findById(parentCommentId).get());

        } else {
            comment.setParentComment(null);
        }
        comment.setCreteTime(new Date());
        return commentRepository.save(comment);
    }
}
