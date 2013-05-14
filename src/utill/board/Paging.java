package utill.board;

import java.util.Map;

/**
 * 페이지 네비게이션
 * @author jinwoo
 * @since 2013. 5 
 * @version 1.0
 * @see Pageging
 * @history
 *  <pre>page : 현재 페이지</pre>
 *  <pre>maxCnt : 총 row 수 </pre>
 *  <pre>rowSet : 리스트에 노출되는 row 수</pre>
 *  <pre>pageSet : 네비게이션에 노출하는 페이지 수</pre>
 *  <pre>noticeSet : 첫페이지에 노출시킬 공지사항 수</pre>
 */
public interface Paging {
    
	/**
	 * Navigation 값 설정
	 * @author jinwoo
	 * @see Paging.setPageNavi
	 * @param  rowSet, pageSet, noticeSet
  	 */
	void setPageNavi(int rowSet, int pageSet, int noticeSet);
 	
	/**
	 * Navigation 관련 필요값 setting
	 * @author jinwoo
	 * @see Paging.getPageNavi
	 * @param  page, maxCnt
	 * @return map ( page:현재페이지, pageLast:마지막페이지, pageSt:페이지리스트 Start, pageEd:페이지리스트End, st, row  )
  	 */
	Map getPageNavi(int page, int maxCnt);
  
  
}
