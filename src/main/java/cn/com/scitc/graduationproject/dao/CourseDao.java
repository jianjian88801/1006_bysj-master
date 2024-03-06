package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CourseDao extends CrudRepository<Course,String> {
    @Query(value ="select * from course where cno =?", nativeQuery = true)
    Course findByCno(Integer cno);

    @Modifying
    @Transactional
    @Query(value="delete from course where cno = ?",nativeQuery = true)
    void deleteByCno(Integer cno);
}
