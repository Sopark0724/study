package com.sopark.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class CommentsTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void crud() throws InterruptedException {

        // Given
        this.createComment(100, "spring data jpa");
        this.createComment(50, "spring data jpa");

        // When
        ListenableFuture<List<Comment>> comments = commentRepository.findByCommentContainsIgnoreCase("Spring");
        log.info("===========");
        log.info("is done?" + comments.isDone());

        comments.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //log.info(throwable);
            }

            @Override
            public void onSuccess(List<Comment> comments) {
                log.info("========Async=======");
                log.info(comments.size()+"");
            }
        });

        Thread.sleep(5000l);
    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);

    }

}