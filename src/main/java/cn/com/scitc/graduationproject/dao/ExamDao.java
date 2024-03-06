package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamDao extends JpaRepository<Exam,Integer> {

    @Query(value ="select * from exam where classid=?", nativeQuery = true)
    List<Exam> finbyclassid(Integer classid);

    @Query(value = "select * from exam where eid=?",nativeQuery = true)
    Exam findByEid(Integer eid);

    @Query(value = "select * from exam where pname= ? limit 1",nativeQuery = true)
    Exam findByPname(String pname);

    @Query(value ="select * from exam ", nativeQuery = true)
    Page<Exam> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="delete from exam where eid = ?",nativeQuery = true)
    void deleteByEid(Integer eid);

    @Modifying
    @Transactional
    @Query(value="delete from exam where classid = ?",nativeQuery = true)
    void deleteByClassid(Integer classid);

    @Modifying
    @Transactional
    @Query(value="delete from exam where cno = ?",nativeQuery = true)
    void deleteByCno(Integer cno);
}
