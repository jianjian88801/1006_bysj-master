package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.StudentScore;
import cn.com.scitc.graduationproject.model.Studentexam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentexamDao extends JpaRepository<Studentexam,Integer> {

    @Query(value ="select * from studentexam where userid=? and eid=?", nativeQuery = true)
    Studentexam findByOne (Integer userid,Integer eid);

    @Query(value ="select * from studentexam where userid=?", nativeQuery = true)
    List<Studentexam> findByUserid (Integer userid);

    @Query(value ="select * from studentexam where  seid=?", nativeQuery = true)
    Studentexam findByseid (Integer seid);

    @Modifying
    @Transactional
    @Query(value="delete from studentexam where eid = ?",nativeQuery = true)
    void deleteByEid(Integer eid);
    //更新pname
    @Modifying
    @Transactional
    @Query(value ="update studentexam set pname =? where eid =?",nativeQuery = true)
    Integer updatePname(String pname,Integer eid);

    @Modifying
    @Transactional
    @Query(value="delete from studentexam where classid = ?",nativeQuery = true)
    void deleteByClassid(Integer classid);
    //分页查询所有学生成绩
    @Query(value = "select * from studentexam where classid = ? ",nativeQuery = true)
    Page<Studentexam> findByClassid(Integer classid, Pageable pageable);


//    @Query(value="select * from studentexam where pname like %?%",nativeQuery = true)
//    public Iterable<Studentexam> findByIdAll(String receiveId);

}
