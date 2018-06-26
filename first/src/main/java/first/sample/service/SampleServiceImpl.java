package first.sample.service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import first.common.util.FileUtils;
import first.sample.dao.SampleDAO;
 
@Service("sampleService")
public class SampleServiceImpl implements SampleService{
    Logger log = Logger.getLogger(this.getClass());
     
    @Resource(name="fileUtils")
    private FileUtils fileUtils;

    @Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
     
    @Override
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
        return sampleDAO.selectBoardList(map);
    }

    @Override
    public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
        sampleDAO.insertBoard(map);
         
        List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for(int i=0, size=list.size(); i<size; i++){
            sampleDAO.insertFile(list.get(i));
        }
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		// 첨부한 파일은 Multipart 형식의 데이터이며, HttpServletRequest에 담겨서 서버로 전송된다.
		// HttpServletRequest 그 자체로는 Multipart형식의 데이터를 조작하는데 어려움이 있기 때문에,
		// MultipartHttpServletRequest 형식으로 형변환을 하였다.
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		// 이터레이터(Iterator) 패턴을 이용하여 request에 전송된 모든 name을 이용하려고 하는 부분이다.
		// Iterator는 어떤 데이터들의 집합체에서 컬렉션(Collection)으로부터 정보를 얻어올 수 있는 인터페이스다.
		// Iterator를 사용하면 집합체와 개별원소들을 분리시켜서 생각할 수 있는데, 그 집합체가 어떤 클래스의 인스턴스인지 신경쓰지 않아도 된다는 장점이 있다.
		// 예를 들어, List나 배열에서는 순차적으로 데이터 접근이 가능하지만, Map,Set 등의 클래스들은 불가능하다.
		// 여기에서 Iterator를 이용하여 Map에 있는 while문을 이용하여 순차적으로 접근하려고 한다.
		MultipartFile multipartFile = null;
		while(iterator.hasNext()) {
			// Iterator 인터페이스의 hasNext() 메서드를 통해 Iterator에 다음 값이 있는 동안 반복해서 다른 작업을 수행한다.(While 문)
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			// hasNext() 메서드는 Iterator 내에 그 다음 데이터의 존재 유무를 알려주고, next() 메서드는 Iterator 내의 데이터를 가져온 후, 커서를 다음위치로 이동시킨다. 

			if(multipartFile.isEmpty() == false) {
			log.debug("------------- file start -------------");
            log.debug("name : "+multipartFile.getName());
            log.debug("filename : "+multipartFile.getOriginalFilename());
            log.debug("size : "+multipartFile.getSize());
            log.debug("-------------- file end --------------\n");
			}
		}
    }
    
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
	    sampleDAO.updateHitCnt(map);
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    Map<String, Object> tempMap = sampleDAO.selectBoardDetail(map);
	    resultMap.put("map", tempMap);
	    
	    List<Map<String, Object>> list = sampleDAO.selectFileList(map);
	    resultMap.put("list", list);
	    return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map) throws Exception {
		sampleDAO.updateBoard(map);
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDAO.deleteBoard(map);
	}

	
}