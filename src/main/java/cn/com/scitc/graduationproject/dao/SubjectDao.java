package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubjectDao  extends JpaRepository<Subject,Integer> {
   //查询单选题
    @Query(value ="select * from subject where stype =?", nativeQuery = true)
    Page<Subject> findByStype(@Param("stype") Integer stype, Pageable pageable);

    @Query(value ="select * from subject where sid =?", nativeQuery = true)
    Subject findBySid (Integer sid);
    @Query(value ="select * from subject where stype =? and cno=?", nativeQuery = true)
    List<Subject> finbytype(Integer stype, Integer cno);

    @Modifying
    @Transactional
    @Query(value="delete from subject where cno = ?",nativeQuery = true)
     void deleteByCno(Integer cno);

}

