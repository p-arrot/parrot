package com.example.parrot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parrot.annotation.AuthCheck;
import com.example.parrot.exception.BusinessException;
import com.example.parrot.model.dto.AddQuestionRequest;
import com.example.parrot.model.dto.QueryQuestionRequest;
import com.example.parrot.model.dto.UpdateQuestionRequest;
import com.example.parrot.model.enums.AuthEnum;
import com.example.parrot.model.vo.QuestionVO;
import com.example.parrot.result.BaseResponse;
import com.example.parrot.result.ErrorCode;
import com.example.parrot.result.ResultUtil;
import com.example.parrot.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/qestion")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @AuthCheck(mustRole = AuthEnum.ADMIN)
    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody AddQuestionRequest addQuestionRequest, HttpServletRequest request) {
        if (addQuestionRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        String title = addQuestionRequest.getTitle();
        String content = addQuestionRequest.getContent();
        String answer = addQuestionRequest.getAnswer();
        List<String> tags = addQuestionRequest.getTags();
        Long id = questionService.addQuestion(title, content, tags, answer, request);
        return ResultUtil.success(id);
    }

    @AuthCheck(mustRole = AuthEnum.ADMIN)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody UpdateQuestionRequest updateQuestionRequest) {
        if (updateQuestionRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        Long id = updateQuestionRequest.getId();
        if (id == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        boolean res = questionService.removeById(id);
        return ResultUtil.success(res);
    }

    @PostMapping("/query")
    public BaseResponse<Page<QuestionVO>> getQuestionVOPage( QueryQuestionRequest queryQuestionRequest) {
        if (queryQuestionRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        Page<QuestionVO> questionVOPage = questionService.getQuestionVOPage(queryQuestionRequest);
        return ResultUtil.success(questionVOPage);
    }

}
