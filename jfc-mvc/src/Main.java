
import controller.UserController;
import model.MyBatisUtil;
import model.UserMapper;
import view.UserView;
import org.apache.ibatis.session.SqlSession;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author taufan
 */

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        
        UserView view = new UserView();
        new UserController(view, mapper);
        
        view.setVisible(true);
    }
}
