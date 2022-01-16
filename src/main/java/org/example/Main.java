package org.example;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;

import java.io.IOException;
import java.io.InputStream;

 @UtilityClass
public class Main {
    private final String resource = "mybatis-config.xml";
   /* public static SqlSession main() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        //NyBatis Configuration file
        //String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;

    }*/

    @SneakyThrows
    public<T> T  getCategoriMaper(Class<T>tClass)
    {

        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource))
                .openSession()
                .getMapper(tClass);
    }
   /* @SneakyThrows
    public ProductsMapper getProductMaper(){
        return main().getMapper(ProductsMapper.class);
    }*/
}
