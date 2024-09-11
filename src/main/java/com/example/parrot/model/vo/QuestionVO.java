package com.example.parrot.model.vo;

import cn.hutool.json.JSONUtil;
import com.example.parrot.exception.BusinessException;
import com.example.parrot.model.entity.Question;
import com.example.parrot.result.ErrorCode;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class QuestionVO {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 推荐答案
     */
    private String answer;

    /**
     * 创建用户 id
     */
    private Long userId;

    public static QuestionVO objToVO(Question question) {
        if(question == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        questionVO.setTags(JSONUtil.toList(question.getTags(),String.class));
        return questionVO;
    }
}
