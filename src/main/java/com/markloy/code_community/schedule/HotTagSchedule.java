package com.markloy.code_community.schedule;

import com.markloy.code_community.dto.TagPriorityDTO;
import com.markloy.code_community.mapper.QuestionMapper;
import com.markloy.code_community.pojo.Question;
import com.markloy.code_community.pojo.QuestionExample;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
@Slf4j
@Data
public class HotTagSchedule {

    private List<TagPriorityDTO> tagPriorityDTOS = new ArrayList<>();

    @Autowired
    private QuestionMapper qm;

    @Value("${hotTag.capacity}")
    private Integer capacity;

    @Value("${hotTag.proportion}")
    private Integer proportion;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 12)
    public void checkHotTag() {
        log.info("HotTagSchedule定时任务开始执行...");
        //获取所有问题
        List<Question> questions = qm.selectByExample(new QuestionExample());
        Map<String, String> tagMap = new HashMap<>();
        for (Question question : questions) {
            //获取问题的tag
            String[] tagList = question.getTag().split(",");
            for (String tag : tagList) {
                if (!tagMap.containsKey(tag)) {
                    //如果map中不包含tag,则新增tag
                    tagMap.put(tag, 1 +"-"+ question.getCommentCount());
                } else {
                    //存在tag，则将tag数值加1
                    String[] split = tagMap.get(tag).split("-");
                    String questionCount = StringUtils.isEmpty(split[0]) ? "0" : split[0];
                    String commentCountMap = StringUtils.isEmpty(split[1]) ? "0" : split[1];
                    tagMap.put(tag, (Integer.parseInt(questionCount) + 1) + "-" + (Integer.parseInt(commentCountMap) + question.getCommentCount()));
                }
            }
        }
        //排序
        tagPriorityDTOS = tagSort(tagMap);
        log.info("HotTagSchedule定时任务执行结束...");
    }

    /**
     * 使用topN的方式实现排序 （小堆顶）
     * @param tagMap
     * @return
     */
    public List<TagPriorityDTO> tagSort(Map<String, String> tagMap) {
        PriorityQueue<TagPriorityDTO> tagPriority = new PriorityQueue<>(capacity);
        tagMap.forEach((tagName, priority) -> {
            String[] split = priority.split("-");
            TagPriorityDTO tagPriorityDTO = new TagPriorityDTO();
            tagPriorityDTO.setTagName(tagName);
            tagPriorityDTO.setQuestionCount(Integer.parseInt(split[0]) * proportion);
            tagPriorityDTO.setCommentCount(Integer.parseInt(split[1]));
            if (tagPriority.size() < capacity) {
                //队列中的元素小于初始长度就放入数据
                tagPriority.add(tagPriorityDTO);
            } else {
                //队列中的元素大于初始长度就拿最小值与当前值比较，如果当前值小于就进入下次比较，如果大于就将队列中的元素替换
                assert tagPriority.peek() != null;
                if (tagPriority.peek().compareTo(tagPriorityDTO) < 0) {
                    tagPriority.poll();
                    tagPriority.add(tagPriorityDTO);
                }
            }
        });
        List<TagPriorityDTO> tagSortList = new ArrayList<>();
        TagPriorityDTO poll = tagPriority.poll();
        while (poll != null) {
            tagSortList.add(0, poll);
            poll = tagPriority.poll();
        }
        return tagSortList;
    }

}
