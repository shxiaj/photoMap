import com.shxiaj.ssm.mapper.PhotoMapper;

import com.shxiaj.ssm.pojo.Photo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //单元测试框架
@ContextConfiguration("classpath:spring.xml") //加载配置文件
public class testMapper {
    //@Autowired
    //private UserMapper userMapper;
    @Autowired
    private PhotoMapper photoMapper;

    //@Test
    //public void testBean() {
    //    List<User> users = userMapper.selectAll();
    //    users.forEach(System.out::println);
    //}

    @Test
    public void testDate() {
        String filename = "image.jpg";
        String fileType = filename.substring(filename.lastIndexOf("."));
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssS");
        filename = "image-" + ft.format(date) + fileType;
        System.out.println(filename);
    }
    @Test
    public void testPhoto() {
        Photo image1 = new Photo(null, "image1", new Date());
        int insert = photoMapper.insert(image1);
        System.out.println(1);
    }    @Test
    public void testPhoto1() {
        Photo photo = photoMapper.selectByPrimaryKey(1);
        System.out.println(photo);
    }
}
