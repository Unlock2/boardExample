package first.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public java.util.List<java.util.Map<String, Object>> selectBoardList(java.util.Map<String, Object> map) {
		return selectList("sample.selectBoardList", map);
	}

	public void insertBoard(Map<String, Object> map) throws Exception{
	    insert("sample.insertBoard", map);
	}

	public void updateHitCnt(Map<String, Object> map) {
		update("sample.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
	    return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) {
		update("sample.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) {
		update("sample.deleteBoard", map);
	}

	public void insertFile(Map<String, Object> map) {
		insert("sample.insertFile", map);
	}
	
}