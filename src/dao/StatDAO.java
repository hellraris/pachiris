package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisConfig;
import vo.Comparison;
import vo.Item;

public class StatDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	SqlSession ss;

	public List<Map<String, Object>> comparisonData(Comparison vo) {

		System.out.println(vo);
		
		List<Map<String, Object>> statList = null;

		try {
			ss = factory.openSession();
			
			int count = ss.selectOne("stat.getCount", vo);
			
			System.out.println("count: " + count);
			
			vo.setCount(count);

			statList = ss.selectList("stat.allSelect", vo);
			
			System.out.println("vo: " + vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			ss.close();

		}

		return statList;

	}

}
