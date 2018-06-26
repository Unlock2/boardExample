package first.sample.dao;
// 패키지의 경로를 나타냄
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;
// 해당 클래스에서 사용될 기능(클래스)를 임포트한 목록 (쉽게 말해 임포트 리스트라고 보면 될듯 싶다)

@Repository("sampleDAO")
// Repository 어노테이션은 컨포넌트 스캔이 XML 설정 없이도 DAO와 레파지토리를 찾고 설정해 준다고 한다.
// 이건 다시 설명을 들어봐야 할것 같다... 
public class SampleDAO extends AbstractDAO{
// 나중에 나올 얘기지만 extends와 impliments의 차이점은 extends는 클래스를 상속받아서 사용하는 것이고, impliments는 인터페이스를 상속받아 사용하는 것이다.
// 여기서는 AbstractDAO 클래스를 상속받아서 SampleDAO 클래스를 선언하였다. 
	@SuppressWarnings("unchecked")
// 컴파일러가 일반적으로 밷는 경고 메세지를 제외시킬때 사용하는 어노테이션이다.
// "unchecked"는 검증되지 않은 연산자 관련 경고를 억제 할때 사용하는 옵션이다.
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
//
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
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)selectList("sample.selectFileList", map);
	}
	
}