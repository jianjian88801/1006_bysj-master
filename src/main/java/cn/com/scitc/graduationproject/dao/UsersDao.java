package cn.com.scitc.graduationproject.dao;

import cn.com.scitc.graduationproject.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {
    //查询老师
    @Query(value ="select * from users where roleid =?", nativeQuery = true)
    Page<Users> findTeacher(Integer roleid,Pageable pageable);
    //查询是该用户名否存在
    @Query(value ="select * from users where username =?", nativeQuery = true)
     Users findByUsername(String username);
    //按userid查询学生
    @Query(value ="select * from users where userid =?", nativeQuery = true)
    Users findByUserid (Integer userid);
    //分页查询所有学生
    @Query(value = "select * from users where classid = ? and roleid=2",nativeQuery = true)
    Page<Users> findByClassid(@Param("classid") Integer classid, Pageable pageable);
    //更新密码
    @Modifying
    @Transactional
    @Query(value ="update Users set userpwd =? where userid =?",nativeQuery = true)
    Integer updatepwd(String userpwd,Integer userid);
    //按userid删除
    @Modifying
    @Transactional
    @Query(value ="delete from users where userid =?",nativeQuery = true)
    Integer deleteByUserid(Integer userid);
    //按班级删除所有学生
    @Modifying
    @Transactional
    @Query(value = "delete from users where classid = ? and roleid=2",nativeQuery = true)
    void deleteByClassid(Integer classid);

    @Override
    public Page<Users> findAll(Pageable pageable);

}
