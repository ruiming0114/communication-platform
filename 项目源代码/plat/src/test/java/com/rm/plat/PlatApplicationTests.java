package com.rm.plat;


import com.rm.plat.mapper.BookScoreMapper;
import com.rm.plat.pojo.TopicCommentReport;
import com.rm.plat.service.*;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlatApplicationTests {

    @Autowired
    BookScoreMapper bookScoreMapper;

    @Test
    public void contextLoads(){
        System.out.println(bookScoreMapper.getScoreByUserAndBook(5, 1));
    }

}
