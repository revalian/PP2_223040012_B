/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import org.apache.ibatis.session.*;
import org.apache.ibatis.io.Resources;
/**
 *
 * @author taufan
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        try {
            sqlSessionFactory =  new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
