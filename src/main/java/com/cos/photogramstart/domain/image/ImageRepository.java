package com.cos.photogramstart.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where userId in(select toUserId from subscribe where fromUserId = :principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Image> mStory(int principalId, Pageable pageable);

    @Query(value ="select i.* from image i inner join (select imageId, count(imageId) likeCount from likes group by imageId order by likeCount desc) c on i.id = c.imageId order by likeCount desc" , nativeQuery = true)
    List<Image> mPopular();
}
