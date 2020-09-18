package com.cmkj.the_link_of_threescreen.repository;

import com.cmkj.the_link_of_threescreen.entity.Thelinkofthreescreenuploadbtn1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // 内置了丰富的条件查询封装类

public interface Thelinkofthreescreenuploadbtn1Repository extends JpaRepository<Thelinkofthreescreenuploadbtn1, Integer>, JpaSpecificationExecutor {
}
