/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author taufan
 */
import java.util.List;
import model.Mahasiswa;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class MahasiswaController {
    public List<Mahasiswa> getAllMahasiswa() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return session.selectList("model.MahasiswaMapper.getAllMahasiswa");
        }
    }

    public void insertMahasiswa(Mahasiswa mahasiswa) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.insert("model.MahasiswaMapper.insertMahasiswa", mahasiswa);
            session.commit();
        }
    }

    public void updateMahasiswa(Mahasiswa mahasiswa) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.update("model.MahasiswaMapper.updateMahasiswa", mahasiswa);
            session.commit();
        }
    }

    public void deleteMahasiswa(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("model.MahasiswaMapper.deleteMahasiswa", id);
            session.commit();
        }
    }
}
