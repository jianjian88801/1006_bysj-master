package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Studentsubject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentsubjectDao extends CrudRepository<Studentsubject,Integer> {
    @Query(value ="select * from studentsubject where userid=? and seid=?", nativeQuery = true)
    List<Studentsubject> findBySeid (Integer userid, Integer seid);

    @Modifying
    @Transactional
    @Query(value="delete from studentsubject where eid = ?",nativeQuery = true)
    void deleteByEid(Integer eid);
}
