package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.ChatDTO;
import com.safefood.dto.CommentDTO;


@Repository("BoardMyBatisRepositoryImpl")
public class BoardMyBatisRepositoryImpl implements IBoardRepository {

	@Autowired
	SqlSession session;

	public BoardMyBatisRepositoryImpl() {

	}

	@Override
	public boolean boardInsert(String bid, String user_id, String btitle, String bcontent, String user_name) {
		BoardDTO m = new BoardDTO();
		m.setBid(Integer.parseInt(bid));
		m.setUser_id(user_id);
		m.setBtitle(btitle);
		m.setBcontent(bcontent);
		m.setUser_name(user_name);
		m.setBcount(0);
		int res = session.insert("com.board.boardInsert", m);
		if (res >= 1)
			return true;
		return false;
	}

	@Override
	public List<BoardDTO> boardList() {
		List<BoardDTO> selectList = session.selectList("com.board.boardList");
		return selectList;
	}

	@Override
	public BoardDTO boardInfo(int bid) {
		BoardDTO board = session.selectOne("com.board.boardInfo", bid+"");
		//System.out.println("여기서" + board.toString());
		return board;
	}

	@Override
	public boolean boardUpdate(String user_id, int bid, String btitle, String bcontent) {
		BoardDTO m = new BoardDTO();
		m.setBid(bid);
		m.setUser_id(user_id);
		m.setBtitle(btitle);
		m.setBcontent(bcontent);
		int res = session.update("com.board.boardUpdate", m);
		if (res >= 1)
			return true;
		return false;
	}

	@Override
	public boolean boardDelete(String user_id, int bid) {
		int res = session.delete("com.board.boardDelete", bid+"");
		if(res>=1) {
			session.update("com.board.boardIndexRefresh1");
			session.update("com.board.boardIndexRefresh2");
			session.update("com.board.boardIndexRefresh3");
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<BoardDTO> boardSearch(String keyword) {

		return null;
	}

	@Override
	public boolean countUp(int bid) {
		int check = session.update("com.board.countUp", bid);
		if(check == 1) 
			return true;
		else
			return false;
	}

	@Override
	public boolean registerComment(int cnum, int bid, String ccontent, String user_id,String user_name) {
		// TODO Auto-generated method stub
		CommentDTO m = new CommentDTO();
		m.setCnum(cnum);
		m.setBid(bid);
		m.setUser_id(user_id);		
		m.setCcontent(ccontent);
		m.setUser_name(user_name);
		//System.out.println(m.toString());
		int res = session.insert("com.board.commentInsert", m);
		if (res >= 1)
			return true;
		return false;
		
	}

	@Override
	public List<CommentDTO> commentList(int bid) {
		// TODO Auto-generated method stub
		List<CommentDTO> selectList = session.selectList("com.board.commentList",bid);
		return selectList;
	}

	@Override
	public boolean commentDelete(int cnum) {
		int res = session.delete("com.board.commentDelete", cnum);
	
	return true;
	}

	@Override
	public boolean registerChat(int cnum, String ccontent, String user_id, String user_name) {
		// TODO Auto-generated method stub
		CommentDTO m = new CommentDTO();
		m.setCnum(cnum);
		m.setUser_id(user_id);		
		m.setCcontent(ccontent);
		m.setUser_name(user_name);
		//System.out.println(m.toString());
		int res = session.insert("com.board.chatInsert", m);
		if (res >= 1)
			return true;
		return false;
	}

	@Override
	public List<ChatDTO> chatList() {
		List<ChatDTO> selectList = session.selectList("com.board.chatList");
		return selectList;
	}

	@Override
	public List<BoardDTO> searchBoardByName(String keyword) {
		List<BoardDTO> selectList = session.selectList("com.board.searchByName", keyword);
		return selectList;
	}

	@Override
	public List<BoardDTO> searchBoardByTitle(String keyword) {
		List<BoardDTO> selectList = session.selectList("com.board.searchByTitle", keyword);
		return selectList;
	}

}
