package utill.board;

import java.util.HashMap;
import java.util.Map;

/**
 * 페이지 네비게이션
 * @author jinwoo
 * @since 2013. 5
 * @version 1.0
 * @see PageNavigation
 * @history
 *  <pre>page : 현재 페이지</pre>
 *  <pre>maxCnt : 총 row 수 </pre>
 *  <pre>rowSet : 리스트에 노출되는 row 수</pre>
 *  <pre>pageSet : 네비게이션에 노출하는 페이지 수</pre>
 *  <pre>noticeSet : 첫페이지에 노출시킬 공지사항 수</pre>
 */
public class PageNavigation implements Paging {
  
	private int rowSet		= 10;
	private int pageSet		= 10;
	private int noticeSet	= 0;
	
	public void setPageNavi(int rowSet, int pageSet, int noticeSet) {
		this.rowSet = rowSet;
		this.pageSet = pageSet;
		this.noticeSet = noticeSet;
	}
	
	public Map getPageNavi(int page, int maxCnt) {
		int pageLast = 0;
		int pageSt = 0;
		int pageEd = 0;
		int st = 0;
		int row = 0; 
		pageLast =  ( maxCnt + noticeSet - 1 ) / rowSet + 1 ;
		pageSt = page - ( ( page-1 ) % pageSet );
		pageEd = pageSt + pageSet - 1 ;
  		if( pageEd > pageLast ) pageEd = pageLast; 
   		
  		if( page == 1 ){
			st = 0;
			row = rowSet - noticeSet;
		}else{
			st = page * rowSet - noticeSet - rowSet;
			row = rowSet;
		}
		Map map = new HashMap();  
  		map.put("page", page);
 		map.put("pageLast", pageLast);
 		map.put("pageSt", pageSt);
 		map.put("pageEd", pageEd);
 		map.put("st", st);
		map.put("row", row); 
   		return map;
 	}
 	
}
