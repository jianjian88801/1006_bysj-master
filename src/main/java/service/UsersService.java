package service;

import cn.com.scitc.graduationproject.model.Users;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
    List<Users> page(Integer classid, Pageable pageable);
}
