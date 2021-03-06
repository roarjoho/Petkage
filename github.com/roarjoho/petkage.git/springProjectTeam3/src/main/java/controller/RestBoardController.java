package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.ReplyDTO;
import service.BoardService;

@RestController
@RequestMapping(value = "/restBoard")
public class RestBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	/* 댓글 목록 */
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST)
	public List<ReplyDTO> getReplyList(@RequestParam("board_numberID") int board_numberID) throws Exception {
		return boardService.getReplyList(board_numberID);
	}
	
	/* 댓글 저장 */
	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody ReplyDTO replyDTO) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.saveReply(replyDTO);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		return result;
	}
	
	/* 댓글 수정  */
	@RequestMapping(value = "/updateReply", method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> updateReply(@RequestBody ReplyDTO replyDTO) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.updateReply(replyDTO);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		return result;
	}
	
	/* 댓글 삭제 */
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public Map<String, Object> deleteReply(@RequestParam("reply_number") int reply_number) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.deleteReply(reply_number);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		return result;
	}
	
}
