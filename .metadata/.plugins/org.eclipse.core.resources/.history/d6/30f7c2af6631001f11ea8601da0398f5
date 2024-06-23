package com.kh.spring.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.PageTemplate;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

   private final NoticeService noticeService;
   
   @GetMapping("noticeList")
   public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
   
   int listCount;      
   int currentPage;   
   int pageLimit;      
   int noticeLimit;   
   int maxPage;      
   int startPage;      
   int endPage;      
   
   listCount = noticeService.noticeCount();
   currentPage = page;
   log.info("게시글의 총 개수 : {}, 현재 요청 페이지 : {}", listCount, currentPage);
   pageLimit = 10;
   noticeLimit = 10;
   maxPage = (int)Math.ceil((double)listCount / noticeLimit);
   startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
   endPage = startPage + pageLimit - 1;
   if(endPage > maxPage) endPage = maxPage;
   
   PageInfo pageInfo = PageInfo.builder()
                        .listCount(listCount)
                        .currentPage(currentPage)
                        .pageLimit(pageLimit)
                        .boardLimit(noticeLimit)
                        .maxPage(maxPage)
                        .startPage(startPage)
                        .endPage(endPage)
                        .build();   
   
   Map<String, Integer> map = new HashMap();
   
   int startValue = (currentPage - 1) * noticeLimit + 1;
   int endValue = startValue + noticeLimit - 1;
   
   map.put("startValue", startValue);
   map.put("endValue", endValue);
   
   List<Notice> noticeList = noticeService.findAll(map);
   /*
   log.info("조회된 게시물의 개수 : {}", boardList.size());
   log.info("-----------------------------------------");
   log.info("조회된 게시글 목록 : {}", boardList);
   */
   model.addAttribute("list", noticeList);
   model.addAttribute("pageInfo", pageInfo);
   
   
   
   return "notice/list";
      
   }
   
   //검색기능(조건 조회 + 페이징 처리_)
   @GetMapping("search")
   public String search(String condition,
                  String keyword,
                  @RequestParam(value="page", defaultValue = "1") int page, Model model) {
      
      log.info("검색 조건 : {}",condition);
      log.info("검색 키워드 : {}",keyword);
      
      Map<String, String> map = new HashMap();
      map.put("condition", condition);
      map.put("keyword", keyword);
      
      int searchCount = noticeService.searchCount(map);
      log.info("검색 조건에 부합하는 행의 수 : {}", searchCount);
      int currentPage = page;
      int pageLimit = 10;
      int boardLimit = 10;
      
      PageInfo pageInfo = PageTemplate.getPageInfo(searchCount,
             currentPage,
             pageLimit,
             boardLimit);
      RowBounds rowBounds = new RowBounds((currentPage - 1) * boardLimit, boardLimit);
      List<Notice> noticeList = noticeService.findByConditionAndKeyword(map, rowBounds);
      
      model.addAttribute("list", noticeList);
      model.addAttribute("pageInfo", pageInfo);
      model.addAttribute("keyword", keyword);
      model.addAttribute("condition", condition);
      
      return "notice/list";
   }
   
   @GetMapping("noticeForm.do")
   public String noticeFormForwording() {
      
      return "notice/insertForm";
   }
   
   @PostMapping("insertForm")
   public String insert(Notice notice, Model model) {
        try {
            noticeService.insert(notice);
            return "redirect:/notice/list";
        } catch (Exception e) {
            log.error("Error inserting notice", e);
            model.addAttribute("message", "게시글 등록 중 오류가 발생했습니다.");
            return "common/errorPage";
        }
    }
}
