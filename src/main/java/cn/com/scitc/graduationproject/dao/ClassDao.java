package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Pjclass;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClassDao extends CrudRepository<Pjclass,String> {
    @Query(value ="select * from pjclass where classid =?", nativeQuery = true)
    Pjclass findByClassid(Integer classid);

    @Modifying
    @Transactional
    @Query(value="delete from pjclass where classid = ?",nativeQuery = true)
    void deleteByClassid(Integer classid);
}
