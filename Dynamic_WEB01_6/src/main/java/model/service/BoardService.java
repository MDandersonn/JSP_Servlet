package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import paging.Paging;

public class BoardService {

	public Paging getPage(int pageNumber, int pageLimit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNumber - 1) * pageLimit + 1);
		map.put("end", pageNumber * pageLimit);
		
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> data = dao.selectPage(map);
		int totalRowCount = dao.totalRowCount();
		int lastPageNumber = (totalRowCount / pageLimit) + (totalRowCount % pageLimit == 0 ? 0 : 1);
		dao.close();
		
		Paging paging = new Paging(data, pageNumber, lastPageNumber, pageLimit, 5);
		return paging;
	}

	public BoardDTO getData(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.selectData(dto);
		dao.close();
		return data;
	}
	
	public boolean incViewCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateViewCnt(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public boolean add(BoardDTO dto, List<BoardImageDTO> boardImageList) {
		BoardDAO dao = new BoardDAO();
		int id = dao.selectNextSeq();
		dto.setId(id);
		int count = dao.insert(dto);
		if(count == 1) {
			if(boardImageList != null) {// 업로드에 이미지가 담겼을경우만 작동.
				for(BoardImageDTO image: boardImageList) {
					image.setBoardId(id);
					dao.insertImage(image);
				}
			}
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public boolean updateData(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.update(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public boolean delete(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		dao.deleteImages(dto);
		int count = dao.delete(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public int delete(List<Integer> arrId) {
		BoardDAO dao = new BoardDAO();
		int count = dao.delete(arrId);
		if(count <= arrId.size()) {
			dao.commit(); dao.close();
			return count;
		}
		dao.rollback(); dao.close();
		return -1;
	}

	public boolean incRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateRecCnt(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public boolean incNRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateNRecCnt(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public boolean decreRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateDecreRecCnt(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}
	
	public boolean decreNRecCnt(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int count = dao.updateDecreNRecCnt(dto);
		if(count == 1) {
			dao.commit(); dao.close();
			return true;
		}
		dao.rollback(); dao.close();
		return false;
	}

	public List<BoardImageDTO> getImages(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		List<BoardImageDTO> data = dao.selectImages(dto);
		dao.close();
		return data;
	}

}
