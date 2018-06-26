package first.sample.controller;
// 패키지 경로 설정. (first / sample / controller)
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.sample.service.SampleService;
// Controller 라는 클래스에서 사용할 기능을 가지고 있는 클래스를 import 하겠다. 라는 의미다.

@Controller
// Controller 어노테이션을 이용하여 해당 클래스를 Controller로 사용하겠다 라고 선언.
public class SampleController {
	// 클래스 이름은 SampleController
    Logger log = Logger.getLogger(this.getClass());
     // Logger 라는 메서드를 log라는 이름으로 쓰겠다. 라고 선언하였으며, 이 log는 이 클래스에서 발생하는 로그를 콘솔에 찍겠다 라는 의미로 이해했다.
    @Resource(name="sampleService")
    // Resource 어노테이션을 사용하여 sampleService 클래스를 가져다 사용하겠다 라는 의미로 이해했다.
    private SampleService sampleService;
    // SampleService 클래스를 sampleService라는 이름으로 해당 클래스에서 사용하겠다 라고 선언하였다.
    @RequestMapping(value="/sample/testMapArgumentResolver.do")
    // RequestMapping 어노테이션을 사용하였으며, 로컬DB에서 실행시 http://localhost:8080/프로젝트명 에서 value값을 뒤에 붙여 웹창에 요청시 해당 기능들이 실행되게 하겠다 라는 의미로 이해했다.
    // RequestMapping 어노테이션은 말 그대로 Request(요청)를(을) Mapping(일치화) 한다라는 의미로 이해했다.
    public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
    	// ModelAndView 라는 형식의 testMapArgumentResolver 라는 이름의 메서드를 생성했다. 받는 파라미터 형식은 CommandMap 형식이며, commandMap이라는 이름으로 파라미터를 받겠다 라고 이해했다.
    
    	// ModelAndView 클래스는 컨트롤러의 처리 결과를 보여 줄 뷰와 뷰에 전달할 값을 저장하는 용도로 사용한단다.
    	
    	// public은 공공재로 사용할 수 있는 타입 (public, private, protected 등의 타입) --> 이를 접근제어자 라고 한단다.
    	// ModelAndView는 내가 알고있는 static(정적함수) 위치에 들어가는 기능(?) 이다.
    	// throws Exception을 뒤에 붙여 예외가 발생할 수 있는 부분을 예외처리 하였다.
        ModelAndView mv = new ModelAndView("");
        //ModelAndView 라는 기능의 함수를 mv로 선언했으며, ("") 는 아무것도 받지 않겠다 라는 의미라고 이해했다.
        if(commandMap.isEmpty() == false){
        	// if 함수를 사용하여 조건을 걸었다. commandMap은 위에 얘기했던대로 ModelAndView 메서드에서 받을 파라미터 값이다. 
        	// 이것이 isEmpty(비었다. 값이 없다.) 라는 것이 거짓이다. 즉, 파라미터값이 존재한다면? 이라고 이해했다. 
            Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
            // Iterator는 자바의 컬렉션(ArrayList, HashMap등등)에서 각각 데이터들의 정보를 얻을 수 있는 인터페이스라고 한다.
            // Iterator 함수를 사용하면 for문을 사용하는 것 보다 구현부분에 독립적이 되기 때문에 Iterator를 사용한다고 한다.
            // Iterator 함수를 String타입의 Key값, Object타입의 Value값으로 명단을 만들겠다 라는 뜻으로 이해했다.
            // 이를 iterator 라는 이름으로 사용하며, 이는 commandMap이라는 파라미터에서 Map형식의 데이터를 가져오고, 명단화한 후 iterator 형식으로 가져라 라는 뜻으로 이해했다.
            Entry<String,Object> entry = null;
            //Entry는 우리가 아는 List,Map 등과 비슷한 콜렉터라고 한다. 여기서 String 타입의 Key값과 Object 타입의 Value값을 가진 Entry를 초기화 시켜주는 것으로 이해했다. null 값으로.
            while(iterator.hasNext()){
            	// while문은 내가 지정한 위치까지 계속해서 반복적으로 돌리는 반복문이다.
            	// iterator에 값을 가지고 있을때 까지 계속해서 반복수행하라는 뜻으로 이해했다. hasNext()는 다음에 결과가 없을때까지 계속 돌려라 라는 뜻이라고 들었다.
                entry = iterator.next();
                //위에 entry는 Entry<String,Object>라고 명시해 놓았으며, 이는 iterator의 값이 있을때 까지. 즉, 들어오는 데이터의 값이 없을때까지 라고 볼 수 있다.
                // 쉽게 얘기해서 위에 있는 대로 받는 파라미터의 값이 없을때까지 계속해서 반복적으로 결과를 수행해라 라는 뜻이라고 이해했다.
                log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
                // log.debug는 쉽게 println 과 비슷한 개념이라고 보면 될거 같다.
                // log가 찍히는 내용은 키값 = entry에서 키 값을 가져와서 출력하라는 것이고, value값 = entry에서 value값을 가져와서 출력하라는 것이다.
                // 위에 선언해 놓았듯이 entry는 Entry<String, Object>의 형식이며, 각각 Key값과 Value값을 의미 한다.
                
            }            
        }
        return mv;
       // 이렇게 수행된 결과 값은 mv 즉, ModelAndView 값을 Return 시켜준다는 얘기로 이해했다. 
    	}
	    
	    @RequestMapping(value="/sample/openBoardList.do")
	    //RequestMapping은 /sample/openBoardList.do 라는 URL을 호출하면 아래 기능이 실행되게끔 연동해 주는 어노테이션이다.
	    public ModelAndView openSampleBoardList(Map<String, Object> commandMap) throws Exception{
	    // ModelAndView 라는 형식의 openSampleBoardList 라는 이름으로 메서드를 생성했다.
	    // 파라미터의 타입은 Map<String, Object> 타입이며, commandMap이라는 이름으로 사용하겠다고 선언했다.
	    // throws Exception을 통해 예외처리를 했다.
	        ModelAndView mv = new ModelAndView("/sample/boardList");
	    // 해당 메서드에서도 ModelAndView를 mv로 선언하였으며, /sample/boardList.jsp를 담고 있겠다.(사용하겠다) 라는 의미로 이해했다.
	    // 이 Controller 클래스에는 ModelAndView를 mv로 선언해 놓은 것이 많은데, 다 각각 다른 객체이다. 각각의 메서드에서 생성자로 선언하였기 때문이다 라고 한다.
	        
	        List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
	    // List를 선언해 주었다. List 안에 Map을 담고 있으며, 이름은 list로 사용하겠다 라고 선언했다.
	    // 이 list는 sampleService 인터페이스 안에 selectBoardList 메서드를 사용한다라고 선언하였으며, commandMap 파라미터값을 가지고 있다. 라고 이해했다.
	    // sampleService는 위에 선언하였지만, Private으로 SampleService 인터페이스 클래스를 sampleService 로 선언하였다는 것을 알 수 있다.
	        mv.addObject("list", list);
	    // ModelAndView를 mv로 선언하였으므로, mv에 addObject 즉, Object를 추가해라 라는 소스이다.
	    // 추가하는 내용은 list 라는 Key값으로 list 라는 Value 값을 넣는다.
	    // 햇갈리지 않을점은 "list"는 mv가 가지는 list값의 이름이 list인 것이고, 실제 데이터 값은 list이다. 즉, sampleService.selectBoardList(commandMap) 값이다.
	        return mv;
	    // 추가한 list 값을 가진 mv를 Return 시켜준다는 것으로 이해했다. 
	    }

        @RequestMapping(value="/sample/openBoardWrite.do")
        // sample/openBoardWrite.do URL을 요청시 아래의 기능들을 호출하는 역할을 하는 어노테이션이다.
        public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
        // ModelAndView 라는 형식의 openBoardWrite 라는 이름으로 메서드를 생성했다.
        // 여기서도 동일하게 CommandMap 타입의 commandMap이라는 이름의 파라미터를 받겠다고 선언했다.
        // throws Exception을 통해 예외처리를 했다.
            ModelAndView mv = new ModelAndView("/sample/boardWrite");
        // ModelAndView를 mv라는 이름으로 생성했으며, sample/boardWrite.jsp를 담고 있겠다.(사용하겠다) 라는 의미로 이해했다. 
            return mv;
        // sample/boardWrite.jsp의 값(혹은 내용)을 담고 있는 상태인 mv를 Return 해준다 라고 이해했다.
        // 여기서는 다른 작업을 거치지 않고 바로 bypass한걸로 보여진다.
         
        }

        @RequestMapping(value="/sample/insertBoard.do")
        // sample/insertBoard.do URL 호출 어노테이션이다. 더이상 자세한 설명은 생략한다..
        public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        // ModelAndView 형식의 insertBoard라는 이름으로 메서드를 생성했다.
        // 파라미터는 CommandMap 타입의 commandMap이라는 이름으로 받겠다는 것과 HttpServletRequest라는 타입의 request라는 이름으로 받겠다는 2개의 파라미터 값이 포함되어 있다.
        // throws Exception을 통해 예외처리를 했다.
            ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
        // ModelAndView를 mv라는 이름으로 생성했으며, sample/openBoardList.do로 재접속해라 라는 것으로 이해했다.
        // 즉 mv는 openBoardList로 재접속해라 라는 이야기로 이해했다.
            sampleService.insertBoard(commandMap.getMap(), request);
        // sampleService 클래스의 insertBoard 매서드에 commandMap 파라미터값을 Map형식으로 변환하여 보내고, HttpServletRequest 형식의 request를 그대로 보내겠다고 선언한 문장이다.
        // 이렇게 되면 2개의 파라미터값을 sampleService클래스의 insertBoard로 보내는 것이라고 이해했다.
            
            return mv;
        // 바로 위의 문장은 독립적으로 돌아가는 문장이므로, 딱히 mv에 데이터를 담거나 하진 않는다.
        // 그렇기 때문에 리턴되는 mv 값은 그 바로 위에 있는 sample/openBoardList.do로 리다이렉트 하라는 것이다.
        // 그러므로 return mv를 하면 openBoardList로 재접속 되는 것이다. 라고 이해했다.
        }
        
        @RequestMapping(value="/sample/openBoardDetail.do")
        // sample/openBoardDetail.do URL 호출 어노테이션이다.
	    public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
        // ModelAndView라는형식의 openBoardDetail 이라는 이름으로 메서드를 선언했다. 파라미터는 CommandMap 형식의 commandMap 이름으로 사용했다.
        // throws Exception으로 예외처리 했다.
	        ModelAndView mv = new ModelAndView("/sample/boardDetail");
	    // ModelAndView 를 mv로 선언했으며, sample/boardDetail.jsp를 담고 있겠다 라는 의미로 이해했다.
	        Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    // map을 선언하는 부분이다. Map<String, Object>의 형식으로 선언했다.
	    // map은 sampleService클래스의 selectBoardDetail 메서드에서 commandMap 파라미터 값을 map형식으로 변환해서 가진 값에 대한 결과값이다. 라고 이해했다.
	        mv.addObject("map", map);
	    // mv에 map이름으로 sampleService.selectBoardDetail(commandMap.getMap()) 에 대한 값을 추가하겠다 라는 내용으로 이해했다.
	        mv.addObject("list", map.get("list"));
	    // mv에 list라는 이름으로 map안에 담겨 있는 list라는 항목의 Value값을 담아서 추가하겠다 라는 내용으로 이해했다.
	         
	        return mv;
	    // 위에서 2개의 addObject한 mv의 값을 Return 하겠다 라는 얘기이다.
	    }
        
        @RequestMapping(value="/sample/openBoardUpdate.do")
        // sample/openBoardUpdate.do 에 대한 URL 호출 어노테이션이다.
        public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
        // ModelAndView라는형식의 openBoardUpdate 라는 이름으로 메서드를 선언했다. 
       	// 파라미터는 CommandMap 형식의 commandMap 이름으로 사용했다.
        // throws Exception으로 예외처리 했다.
            ModelAndView mv = new ModelAndView("/sample/boardUpdate");
        // ModelAndView를 mv로 선언하였으며, sample/boardUpdate.jsp를 담고 있겠다 라는 의미이다.
             
            Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
        // 여기서도 여지없이 map을 Stirng,Object 타입으로 선언했고 
        // 이는 sampleService 클래스의 selectBoardDetail 메서드에 commandMap파라미터 값을 map형식으로 변환해서 넣은 값을 나타낸다.
            mv.addObject("map", map.get("map"));
        // mv에 map이라는 이름으로 sampleService.selectBoardDetail(commandMap.getMap()) 값 안에 있는 map이라는 항목을 가져와서 추가하겠다 라는 내용으로 이해했다.
            mv.addObject("list", map.get("list"));
        // mv에 list라는 이름으로 sampleService.selectBoardDetail(commandMap.getMap()) 값 안에 있는 list 라는 항목을 가져와서 추가하겠다 라는 내용으로 이해했다.
            return mv;
        // 위의 2가지 addObject 한 mv 값을 Return 하겠다 라는 얘기이다.   
         
        }

        @RequestMapping(value="/sample/updateBoard.do")
        // sample/updateBoard.do URL에 대한 호출 어노테이션이다.
        public ModelAndView updateBoard(CommandMap commandMap) throws Exception{
        // 또 똑같으니 넘어가자.
            ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
        // 이것도 위에서 한번 언급하였으나 다시 한번 얘기 해 보도록 하자.
        // ModelAndView를 mv로 선언하였으며, sample/openBoardDetail.do 에 재접속하라는 내용을 포함하고 있다.
             
            sampleService.updateBoard(commandMap.getMap());
        // sampleService 클래스의 updateBoard 메서드에 commandMap 파라미터 값을 map형식으로 변환하여 넣은 값을 호출하라는 내용으로 이해했다.
            mv.addObject("IDX", commandMap.get("IDX"));
        // IDX라는 이름으로 commandMap 파라미터 값에서 IDX 값을 찾은 후 mv에 값을 추가해라 라는 내용으로 이해했다.
            return mv;
        // sampleService.updateBoard(commandMap.getMap()); 에 대한 내용은 따로 돌아가는 명령어 소스이다.
        // mv는 IDX의 값이 추가된 mv 값을 Return 해준다는 내용이다.
         
        }
        
        @RequestMapping(value="/sample/deleteBoard.do")
        // sample/deleteBoard.do URL에 대한 호출 어노테이션이다.
        public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
        // 또 나왔다.. 그냥 넘어가자.
            ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
        // 이것도 비슷하지만 복습하자는 의미에서 다시한번 적어보자.
        // ModelAndView를 mv 선언하였으며, mv는 sample/openBoardList.do에 재접속하라는 내용을 포함하고 있다.
             
            sampleService.deleteBoard(commandMap.getMap());
        // sampleService 클래스의 deleteBoard 메서드에 commandMap 파라미터 값을 map형식으로 변환하여 넣은 값을 호출하라는 내용이다.
             
            return mv;
        // 결과적으로 mv 는 openBoardList.do 라는 URL에 재접속하는 값을 Return 해 준다고 볼 수 있다.
            
        }
    }
